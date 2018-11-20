package iview.wsienski.restexample.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import iview.wsienski.restexample.data.APIService
import iview.wsienski.restexample.data.model.User
import javax.inject.Inject

class MainViewModel
@Inject constructor(private val apiService: APIService) : ViewModel(), IMainViewModel {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    override val users: MutableLiveData<List<User>> = MutableLiveData()
    override fun init() {
        compositeDisposable.add(apiService.listUsers(10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t -> users.value = t })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}
