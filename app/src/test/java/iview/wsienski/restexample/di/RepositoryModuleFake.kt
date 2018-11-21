package iview.wsienski.restexample.di

import iview.wsienski.restexample.data.APIService
import iview.wsienski.restexample.data.repository.IUserRepository
import iview.wsienski.restexample.data.repository.UserRepositoryFake

/**
 * Created by Witold Sienski on 21.11.2018.
 */
class RepositoryModuleFake : RepositoryModule() {
    override fun provideUsersRepository(apiService: APIService): IUserRepository = UserRepositoryFake(true)
}