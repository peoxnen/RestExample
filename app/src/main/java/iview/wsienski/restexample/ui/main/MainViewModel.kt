package iview.wsienski.restexample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import iview.wsienski.restexample.data.APIService
import javax.inject.Inject

class MainViewModel
@Inject constructor(private val apiService: APIService) : ViewModel(), IMainViewModel {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    override val users: MutableLiveData<List<UserView>> = MutableLiveData()

    override fun init() {
        if (users.value != null) return
        compositeDisposable.add(apiService.listUsers(10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.map { UserView(it.login, it.avatar_url) } }
            .subscribe { t -> users.value = t })
    }

    override val usersAvatars: LiveData<List<String>> =
        LiveDataReactiveStreams.fromPublisher(
            apiService.listUsers(10)
                .map { it.map { it.login } }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable(BackpressureStrategy.LATEST))

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}
