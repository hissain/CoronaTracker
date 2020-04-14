package bd.ctracker.com.ctracker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import bd.ctracker.com.ctracker.model.CTEventInfo
import bd.ctracker.com.ctracker.model.CTLocation
import bd.ctracker.com.ctracker.repository.RestApiService
import bd.ctracker.com.ctracker.utility.PreferenceKeys
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.*
import timber.log.Timber

class TrackingUpdateBroadcastReceiver: BroadcastReceiver() {

    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    override fun onReceive(context: Context?, intent: Intent?) {

        Timber.d( "onReceive() context:$context, intent:$intent")
        if (intent != null) {
            if(intent.action == ACTION_PROCESS_UPDATES) {
                LocationResult.extractResult(intent)?.let { locationResult ->

                    val locations = locationResult.locations.map { location ->
                        CTLocation(
                            latitude = location.latitude,
                            longitude = location.longitude,
                            altitude = location.altitude
                        )
                    }
                    if (locations.isNotEmpty()) {
                        var loc: String = ""
                        for(location in locations) {

                            loc += "Lat: ${location.latitude} -- lon: ${location.longitude} -- alt: ${location.altitude} \n"
                            Timber.i( "Location ${location.latitude}, ${location.longitude} & ${location.altitude}")

                            scope.launch {
                                // Save close contact data/Send to data to server
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(
                                        context,
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
                        Timber.d("Location updates :-\n $loc");
                    }
                }
            }

        }
    }

    companion object {
        const val ACTION_PROCESS_UPDATES =
            "bd.ctracker.com.ctracker.action." + "PROCESS_UPDATES"
    }
}