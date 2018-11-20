package iview.wsienski.restexample.ui.main

import androidx.lifecycle.LiveData
import iview.wsienski.restexample.data.model.User

/**
 * Created by Witold Sienski on 20.11.2018.
 */
interface IMainViewModel {
   val users: LiveData<List<User>>
    fun init()
}