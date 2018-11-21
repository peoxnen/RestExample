package iview.wsienski.restexample.di

import dagger.Module
import dagger.Provides
import iview.wsienski.restexample.common.ISchedulersProvider
import iview.wsienski.restexample.common.SchedulersProviderProd
import iview.wsienski.restexample.data.APIService
import iview.wsienski.restexample.data.repository.IUserRepository
import iview.wsienski.restexample.data.repository.UsersRepository
import javax.inject.Singleton

/**
 * Created by Witold Sienski on 21.11.2018.
 */
@Module
open class RepositoryModule {

    @Provides
    @Singleton
    open fun provideUsersRepository(apiService: APIService): IUserRepository = UsersRepository(apiService)

    @Provides
    @Singleton
    open fun provideSchedulersProbider(): ISchedulersProvider = SchedulersProviderProd()

}