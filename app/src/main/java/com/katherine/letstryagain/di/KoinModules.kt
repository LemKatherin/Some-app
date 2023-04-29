package com.katherine.letstryagain.di

import android.accounts.AccountManager
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.katherine.common.data.preferences.PreferencesApi
import com.katherine.common.data.preferences.PreferencesApiImpl
import com.katherine.common.data.remote.api.UserApi
import com.katherine.common.data.remote.interceptor.AuthInterceptor
import com.katherine.common.data.remote.interceptor.InternetConnectionInterceptor
import com.katherine.common.data.remote.responseWrapper.ApiCall
import com.katherine.common.data.remote.responseWrapper.ApiCallImpl
import com.katherine.common.utils.datastore.DataStoreUtils
import com.katherine.common.utils.network.Builders
import com.katherine.common.utils.network.Builders.createApi
import com.katherine.letstryagain.BuildConfig
import com.katherine.letstryagain.data.dataSource.local.UserLocalDataSource
import com.katherine.letstryagain.data.dataSource.local.UserLocalDataSourceImpl
import com.katherine.letstryagain.data.dataSource.user.UserRemoteDataSource
import com.katherine.letstryagain.data.dataSource.user.UserRemoteDataSourceImpl
import com.katherine.letstryagain.data.repository.user.UserRepository
import com.katherine.letstryagain.data.repository.user.UserRepositoryImpl
import com.katherine.letstryagain.mvi.viewModel.login.LoginViewModel
import com.katherine.letstryagain.mvi.viewModel.main.MainViewModel
import com.katherine.letstryagain.mvi.viewModel.notes.NotesViewModel
import com.katherine.letstryagain.mvi.viewModel.settngs.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.converter.jackson.JacksonConverterFactory

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::NotesViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::MainViewModel)
}

val preferencesModule = module {
    single { DataStoreUtils.createDataStore(androidContext()) }
    single<PreferencesApi> { PreferencesApiImpl(get()) }
}

val apiModule = module {
    single<ApiCall> { ApiCallImpl() }
    single { createApi(get(), UserApi::class.java) }
}

val interceptorModule = module {
    singleOf(::InternetConnectionInterceptor)
    singleOf(::AuthInterceptor)
}

val networkModule = module {
    single {
        Builders.buildOkHttpClient(
            androidContext(),
            get<InternetConnectionInterceptor>(),
            get<AuthInterceptor>()
        )
    }
    single { Builders.buildRetrofit(BuildConfig.API_ROUTE, get(), get(), get()) }
    single<Converter.Factory> {
        JacksonConverterFactory.create(
            ObjectMapper().registerModule(
                KotlinModule.Builder().build()
            )
        )
    }
    single<CallAdapter.Factory> { CoroutineCallAdapterFactory() }
    includes(apiModule, interceptorModule)
}


val accountModule = module {
    single { AccountManager.get(androidContext()) }
}

val remoteDataSourceModule = module {
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get(), get()) }
}

val localDataSourceModule = module {
    single<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }
}

val dataSourceModule = module {
    includes(remoteDataSourceModule, localDataSourceModule)
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
}

val allModules =
    listOf(
        preferencesModule,
        viewModelModule,
        networkModule,
        dataSourceModule,
        repositoryModule,
        accountModule
    )