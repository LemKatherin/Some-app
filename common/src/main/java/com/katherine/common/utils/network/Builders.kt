package com.katherine.common.utils.network

import android.content.Context
import com.katherine.common.data.remote.interceptor.InternetConnectionInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

object Builders {
    fun buildOkHttpClient(context: Context, vararg interceptors: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(InternetConnectionInterceptor(context))
            .apply {
                interceptors.forEach { interceptor ->
                    addInterceptor(interceptor)
                }
            }
            .build()

    fun buildRetrofit(
        url: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        adapterFactory: CallAdapter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(adapterFactory)
        .build()

    fun <T> createApi(retrofit: Retrofit, cl: Class<T>): T = retrofit.create(cl)
}