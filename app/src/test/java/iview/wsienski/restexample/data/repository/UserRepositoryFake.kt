package iview.wsienski.restexample.data.repository

import io.reactivex.Observable
import iview.wsienski.restexample.data.model.User

/**
 * Created by Witold Sienski on 21.11.2018.
 */
class UserRepositoryFake(isSuccess: Boolean) : IUserRepository {
    override val users: Observable<List<User>> =
        if (isSuccess) Observable.just(
            listOf(
                User(login = "login1", avatar_url = "http://goo.gl/gEgYUd"),
                User(login = "login2", avatar_url = "http://goo.gl/gEgYUd")
            )
        )
        else Observable.error(Error("Error"))

    override val repoUrls: Observable<List<String>> = Observable.just(listOf("URL_1", "URL_2"))
}