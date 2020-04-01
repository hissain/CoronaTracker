package bd.ctracker.com.ctracker.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.work.*
import bd.ctracker.com.ctracker.R
import bd.ctracker.com.ctracker.model.CTUserInfo
import bd.ctracker.com.ctracker.repository.RestApiService
import bd.ctracker.com.ctracker.service.ContactService
import bd.ctracker.com.ctracker.utility.LOCATION_WORK_TAG
import bd.ctracker.com.ctracker.worker.TrackContactWorker
import kotlinx.android.synthetic.main.activity_registration.*
import timber.log.Timber
import java.util.concurrent.TimeUnit
import android.provider.Settings.Secure;

private const val MY_PERMISSIONS_REQUEST_LOCATION = 1000

class RegistrationActivity : AppCompatActivity() {

    private val serviceIntent by lazy {
        Intent(this, ContactService::class.java)
    }

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus?.windowToken, 0
        )
    }

    fun setupUI(view: View) { // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener(OnTouchListener { v, event ->
                hideSoftKeyboard(this@RegistrationActivity)
                false
            })
        }
        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until (view as ViewGroup).childCount) {
                val innerView: View = (view as ViewGroup).getChildAt(i)
                setupUI(innerView)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Complete Registation")
        setContentView(R.layout.activity_registration)
        setupUI(this.getWindow().getDecorView().findViewById(android.R.id.content))


        btn_reg.setOnClickListener {

            val name = findViewById<EditText>(R.id.et_name).text.toString()
            val number = findViewById<EditText>(R.id.et_number).text.toString()
            val nid = findViewById<EditText>(R.id.et_nid).text.toString()

            val duid = Secure.getString(this.contentResolver, Secure.ANDROID_ID)

            if (name.isBlank() || number.isBlank() || nid.isBlank()){
                print("Invalid input. Please check and enter valid name, number and nid")
                return@setOnClickListener
            }

            val apiService = RestApiService()

            var userInfo = CTUserInfo(id = null, name = name, phoneNumber = number, nationalID = nid, userDuid = duid)

            apiService.addUser(userInfo)

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
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Timber.d("Starting service")

                    startService(serviceIntent)

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
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ),
                        MY_PERMISSIONS_REQUEST_LOCATION
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        stopService(serviceIntent)
        super.onDestroy()
    }
}
