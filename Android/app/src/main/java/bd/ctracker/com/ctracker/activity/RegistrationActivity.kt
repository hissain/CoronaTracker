package bd.ctracker.com.ctracker.activity

import android.app.Activity
import android.os.Bundle
import android.provider.Settings.Secure
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import bd.ctracker.com.ctracker.R
import bd.ctracker.com.ctracker.model.CTUserInfo
import bd.ctracker.com.ctracker.repository.RestApiService
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus?.windowToken, 0
        )
    }

    private fun setupUI(view: View) { // Set up touch listener for non-text box views to hide keyboard.
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
        title = "Complete Registration"
        setContentView(R.layout.activity_registration)
        setupUI(this.window.decorView.findViewById(android.R.id.content))


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
            val userInfo = CTUserInfo(id = null, name = name, phoneNumber = number, nationalID = nid, userDuid = duid)

            apiService.addUser(userInfo)
        }
    }
}
