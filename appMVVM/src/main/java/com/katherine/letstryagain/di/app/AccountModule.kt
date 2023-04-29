package com.katherine.letstryagain.di.app

import android.accounts.AccountManager
import android.content.Context
import com.katherine.letstryagain.data.account.AccountAuthenticator
import com.katherine.letstryagain.data.dataSource.user.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AccountModule {

    @Singleton
    @Provides
    fun provideAccountManager(
        @ApplicationContext context: Context
    ): AccountManager =
        AccountManager.get(context)

    @Singleton
    @Provides
    fun provideAccountAuthenticator(
        @ApplicationContext context: Context,
        userRemoteDataSource: UserRemoteDataSource
    ): AccountAuthenticator = AccountAuthenticator(context, userRemoteDataSource)

}