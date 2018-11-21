package iview.wsienski.restexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import iview.wsienski.restexample.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            val newInstance = supportFragmentManager.findFragmentByTag(TAG) ?: MainFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, newInstance, TAG)
                .commitNow()
        }
    }

    companion object {
        const val TAG = "MAIN_FRAGMENT_TAG"
    }

}
