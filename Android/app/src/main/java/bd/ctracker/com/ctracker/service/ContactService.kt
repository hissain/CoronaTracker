package bd.ctracker.com.ctracker.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.work.WorkManager
import bd.ctracker.com.ctracker.BaseApplication
import bd.ctracker.com.ctracker.model.CTEventInfo
import bd.ctracker.com.ctracker.repository.RestApiService
import bd.ctracker.com.ctracker.utility.LOCATION_WORK_TAG
import bd.ctracker.com.ctracker.utility.PreferenceKeys
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

                        val id = BaseApplication.preference().getInt(PreferenceKeys.userID, -1)

                        if (id != -1) { // registered valid user

                            val eventInfo = CTEventInfo(
                                null,
                                userId = id,
                                latitude = location.latitude,
                                longitude = location.longitude,
                                altitude = location.altitude
                            )

                            RestApiService().addEvent(eventInfo, onResult = {

                                if (it != null) {
                                    Timber.d(
                                        "Event added for user %s, event id: %s",
                                        id,
                                        it.eventId
                                    )
                                } else {
                                    Timber.e("Event could not be added. Need to check at Server side")
                                }
                            })
                        }
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
