package bd.ctracker.com.ctracker

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import timber.log.Timber

class BaseApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: BaseApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        fun preference() : SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(applicationContext())
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}