package bd.ctracker.com.ctracker.worker

import android.content.Context
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class TrackContactWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        val appContext = applicationContext

        return try {
            // Logic for tracking background location, Bluetooth, Sensor etc
            // getDataFromDatabase()
            // sendDataToServer()
            withContext(Dispatchers.Main) {
                Toast.makeText(appContext, "Sending data to server...", Toast.LENGTH_LONG)
                Timber.d("Sending data to server")
            }
            Result.success()
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            Result.failure()
        }
    }
}