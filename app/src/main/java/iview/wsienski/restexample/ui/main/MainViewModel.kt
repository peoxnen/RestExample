package iview.wsienski.restexample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
import iview.wsienski.restexample.common.ISchedulersProvider
import iview.wsienski.restexample.data.repository.IUserRepository
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    private val usersRepository: IUserRepository,
    private val schedulersProvider: ISchedulersProvider
) : ViewModel(), IMainViewModel {

    override val screenState: MutableLiveData<ScreenState> = MutableLiveData()
    override val users: MutableLiveData<List<UserView>> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun init() {
        if (users.value != null) return
        compositeDisposable.add(usersRepository.users
            .doOnSubscribe { screenState.postValue(ScreenState(true)) }
            .doOnComplete { screenState.postValue(ScreenState(false)) }
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
            .map { users -> users.map { UserView(it.login, it.avatar_url) } }
            .subscribe(
                { list -> users.value = list },
                { error -> screenState.value = ScreenState(error = error.message) })
        )
    }

    override val usersRepo: LiveData<String> =
        LiveDataReactiveStreams.fromPublisher(
            usersRepository.repoUrls
                .map { it.joinToString(separator = "\n") }
                .onErrorReturn { "" }
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .toFlowable(BackpressureStrategy.LATEST))

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}
