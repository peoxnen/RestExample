package iview.wsienski.restexample.data.repository

import io.reactivex.Observable
import iview.wsienski.restexample.data.APIService
import iview.wsienski.restexample.data.model.User

/**
 * Created by Witold Sienski on 21.11.2018.
 */
class UsersRepository(apiService: APIService) : IUserRepository {

    override val users: Observable<List<User>> = apiService.listUsers(10)
    override val usersIds: Observable<List<String>> = apiService.listUsers(10)
        .map { it.map { it.login } }
}