package bd.ctracker.com.ctracker.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import bd.ctracker.com.ctracker.R


class RegistrationActivity : AppCompatActivity() {

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus.windowToken, 0
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
    }
}
