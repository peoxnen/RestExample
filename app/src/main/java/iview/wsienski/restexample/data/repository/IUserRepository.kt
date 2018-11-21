package iview.wsienski.restexample.data.repository

import io.reactivex.Observable
import iview.wsienski.restexample.data.model.User

/**
 * Created by Witold Sienski on 21.11.2018.
 */
interface IUserRepository {
    val users: Observable<List<User>>
    val repoUrls: Observable<List<String>>
}