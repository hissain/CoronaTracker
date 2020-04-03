package bd.ctracker.com.ctracker.manager

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.MainThread
import bd.ctracker.com.ctracker.TrackingUpdateBroadcastReceiver
import bd.ctracker.com.ctracker.utility.hasPermission
import com.google.android.gms.location.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class TrackingManager private constructor(private val context: Context){

    private val mFusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private val locationRequest: LocationRequest = LocationRequest().apply {
        interval = TimeUnit.SECONDS.toMillis(30)
        fastestInterval = TimeUnit.SECONDS.toMillis(10)
        maxWaitTime = TimeUnit.SECONDS.toMillis(2)
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private val locationUpdatePendingIntent: PendingIntent by lazy {
        val intent = Intent(context, TrackingUpdateBroadcastReceiver::class.java)
        intent.action = TrackingUpdateBroadcastReceiver.ACTION_PROCESS_UPDATES
        PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    @Throws(SecurityException::class)
    @MainThread
    fun startLocationUpdates() {

        Timber.d( "startLocationUpdates()")
        if (!context.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) return

        try {
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationUpdatePendingIntent)
        } catch (permissionRevoked: SecurityException) {
            Timber.d( "Location permission revoked; details: $permissionRevoked")
            throw permissionRevoked
        }
    }

    @MainThread
    fun stopLocationUpdates() {
        Timber.d( "stopLocationUpdates()")
        mFusedLocationClient.removeLocationUpdates(locationUpdatePendingIntent)
        //TODO:: Need to send broadcast to cancel the coroutine. coroutine.cancel() in TrackingUpdateBroadcastReceiver
    }


    companion object {
        @Volatile private var INSTANCE: TrackingManager? = null

        fun getInstance(context: Context): TrackingManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: TrackingManager(context).also { INSTANCE = it }
            }
        }
    }
}
