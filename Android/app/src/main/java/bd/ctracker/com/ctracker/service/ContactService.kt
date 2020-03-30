package bd.ctracker.com.ctracker.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.work.WorkManager
import bd.ctracker.com.ctracker.utility.LOCATION_WORK_TAG
import com.google.android.gms.location.*
import kotlinx.coroutines.*
import timber.log.Timber

class ContactService : Service() {

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        Timber.d("Service Created")
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 300 * 1000 // 5 minutes
        locationRequest.fastestInterval = 150 * 1000 // 2.5 minutes

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    Timber.i("Location %s", location)
                    coroutineScope.launch {
                        // Save close contact data/Send to data to server
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@ContactService,
                                "Getting contact details and stored into DB...",
                                Toast.LENGTH_SHORT
                            )
                        }
                        Timber.d("Getting contact details and stored into DB")

                    }
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.d("Service Started")
        requestLocationUpdates()
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        mFusedLocationClient.removeLocationUpdates(locationCallback)
        WorkManager.getInstance(this).cancelAllWorkByTag(LOCATION_WORK_TAG)
        coroutineScope.cancel()
        Timber.e("Service Destroyed")
        super.onDestroy()
    }

    private fun requestLocationUpdates() {
        mFusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }
}
