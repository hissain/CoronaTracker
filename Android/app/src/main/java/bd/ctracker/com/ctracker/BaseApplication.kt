package bd.ctracker.com.ctracker

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

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
}