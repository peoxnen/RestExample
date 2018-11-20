package iview.wsienski.restexample.di

import dagger.Component
import iview.wsienski.restexample.RestExampleApp
import iview.wsienski.restexample.ui.main.MainFragment
import javax.inject.Singleton

/**
 * Created by Witold Sienski on 20.11.2018.
 */
@Singleton
@Component(modules = [NetModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(restExampleApp: RestExampleApp)
    fun inject(restExampleApp: MainFragment)

}
