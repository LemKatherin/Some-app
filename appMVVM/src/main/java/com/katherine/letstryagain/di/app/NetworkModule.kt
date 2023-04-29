package com.katherine.letstryagain.di.app

import android.content.Context
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.katherine.common.data.remote.interceptor.AuthInterceptor
import com.katherine.common.data.remote.interceptor.InternetConnectionInterceptor
import com.katherine.common.data.remote.responseWrapper.ApiCall
import com.katherine.common.data.remote.responseWrapper.ApiCallImpl
import com.katherine.common.utils.network.Builders.buildOkHttpClient
import com.katherine.common.utils.network.Builders.buildRetrofit
import com.katherine.letstryagain.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiCall(): ApiCall = ApiCallImpl()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        adapterFactory: CallAdapter.Factory
    ): Retrofit =
        buildRetrofit(BuildConfig.API_ROUTE, okHttpClient, converterFactory, adapterFactory)

    @Singleton
    @Provides
    fun provideInternetConnectionInterceptor(@ApplicationContext context: Context) =
        InternetConnectionInterceptor(context)

    @Singleton
    @Provides
    fun provideAuthInterceptor() = AuthInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        internetConnectionInterceptor: InternetConnectionInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient = buildOkHttpClient(context, internetConnectionInterceptor, authInterceptor)

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory = JacksonConverterFactory.create(
        ObjectMapper().registerModule(
            KotlinModule.Builder().build()
        )
    )

    @Singleton
    @Provides
    fun provideAdapterFactory(): CallAdapter.Factory = CoroutineCallAdapterFactory()

}