package bd.ctracker.com.ctracker.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.work.*
import bd.ctracker.com.ctracker.R
import bd.ctracker.com.ctracker.manager.TrackingManager
import bd.ctracker.com.ctracker.utility.LOCATION_WORK_TAG
import bd.ctracker.com.ctracker.worker.TrackContactWorker
import timber.log.Timber
import java.util.concurrent.TimeUnit

private const val MY_PERMISSIONS_REQUEST_LOCATION = 1000

class TrackingActivity : AppCompatActivity() {

    private val serviceIntent by lazy {
        Intent(this, TrackingManager::class.java)
    }

    private lateinit var trackingManager: TrackingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Tracking"
        setContentView(R.layout.activity_tracker)

        trackingManager = TrackingManager.getInstance(this)

        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q){
            requestAccessFineLocation()
        } else{
            requestAccessBackgroundLocation()
        }
    }

    private fun requestAccessFineLocation() {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                MY_PERMISSIONS_REQUEST_LOCATION
            )
        } else {
            startService(serviceIntent)
        }
    }

    private fun requestAccessBackgroundLocation(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ),
                MY_PERMISSIONS_REQUEST_LOCATION
            )
        } else {
            startService(serviceIntent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Timber.d("Starting service")

                    //startService(serviceIntent)
                    trackingManager.startLocationUpdates()

                    val locationWorker =
                        PeriodicWorkRequestBuilder<TrackContactWorker>(15, TimeUnit.MINUTES)
                            .setConstraints(
                                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                                    .build()
                            )
                            .addTag(LOCATION_WORK_TAG)
                            .build()

                    WorkManager
                        .getInstance(this)
                        .enqueueUniquePeriodicWork(
                            LOCATION_WORK_TAG,
                            ExistingPeriodicWorkPolicy.REPLACE,
                            locationWorker
                        )

                } else {
                    if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q){
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            ),
                            MY_PERMISSIONS_REQUEST_LOCATION
                        )
                    } else {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION
                            ),
                            MY_PERMISSIONS_REQUEST_LOCATION
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        stopService(serviceIntent)
        super.onDestroy()
    }
}
