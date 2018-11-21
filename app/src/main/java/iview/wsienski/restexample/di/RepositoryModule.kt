package iview.wsienski.restexample.di

import dagger.Module
import dagger.Provides
import iview.wsienski.restexample.data.APIService
import iview.wsienski.restexample.data.repository.IUserRepository
import iview.wsienski.restexample.data.repository.UsersRepository
import javax.inject.Singleton

/**
 * Created by Witold Sienski on 21.11.2018.
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUsersRepository(apiService: APIService): IUserRepository = UsersRepository(apiService)
}