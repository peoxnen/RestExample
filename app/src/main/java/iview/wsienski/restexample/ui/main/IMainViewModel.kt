package iview.wsienski.restexample.ui.main

import androidx.lifecycle.LiveData

/**
 * Created by Witold Sienski on 20.11.2018.
 */
interface IMainViewModel {
    val users: LiveData<List<UserView>>
    val usersAvatars: LiveData<List<String>>
    fun init()
}