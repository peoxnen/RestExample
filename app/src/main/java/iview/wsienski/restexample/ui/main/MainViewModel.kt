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

    override val screenState: MutableLiveData<ScreenState> = MutableLiveData()
    override val users: MutableLiveData<List<UserView>> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun init() {
        if (users.value != null) return
        compositeDisposable.add(apiService.listUsers(10)
            .doOnSubscribe { screenState.postValue(ScreenState(true)) }
            .doOnComplete { screenState.postValue(ScreenState(false)) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.map { UserView(it.login, it.avatar_url) } }
            .subscribe(
                { list -> users.value = list },
                { error -> screenState.postValue(ScreenState(error = error.message)) })
        )
    }

    override val usersAvatars: LiveData<List<String>> =
        LiveDataReactiveStreams.fromPublisher(
            apiService.listUsers(10)
                .map { it.map { it.login } }
                .onErrorReturn { emptyList() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toFlowable(BackpressureStrategy.LATEST))

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}
