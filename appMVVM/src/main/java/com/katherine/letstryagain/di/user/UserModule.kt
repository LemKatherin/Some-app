package com.katherine.letstryagain.di.user

import android.accounts.AccountManager
import com.katherine.common.data.remote.api.UserApi
import com.katherine.common.data.remote.responseWrapper.ApiCall
import com.katherine.common.utils.network.Builders.createApi
import com.katherine.letstryagain.data.dataSource.local.UserLocalDataSource
import com.katherine.letstryagain.data.dataSource.local.UserLocalDataSourceImpl
import com.katherine.letstryagain.data.dataSource.user.UserRemoteDataSource
import com.katherine.letstryagain.data.dataSource.user.UserRemoteDataSourceImpl
import com.katherine.letstryagain.data.repository.user.UserRepository
import com.katherine.letstryagain.data.repository.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UserModule {

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(
        asyncCall: ApiCall,
        userApi: UserApi
    ): UserRemoteDataSource =
        UserRemoteDataSourceImpl(asyncCall, userApi)

    @Singleton
    @Provides
    fun provideUserLocalDataSource(
        accountManager: AccountManager
    ): UserLocalDataSource = UserLocalDataSourceImpl(accountManager)

    @Singleton
    @Provides
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource,
        userLocalDataSource: UserLocalDataSource
    ): UserRepository =
        UserRepositoryImpl(userRemoteDataSource, userLocalDataSource)

    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi = createApi(retrofit, UserApi::class.java)

}