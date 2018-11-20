package iview.wsienski.restexample

import android.app.Application
import iview.wsienski.restexample.di.AppComponent
import iview.wsienski.restexample.di.DaggerAppComponent
import iview.wsienski.restexample.di.NetModule


/**
 * Created by Witold Sienski on 20.11.2018.
 */
class RestExampleApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .netModule(NetModule(getString(R.string.base_url)))
            .build()
        appComponent.inject(this)
    }

}