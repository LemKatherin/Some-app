package com.katherine.common.data.remote.interceptor

import android.content.Context
import com.katherine.common.data.remote.exceptions.InternetConnectionException
import com.katherine.common.utils.network.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response

class InternetConnectionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        if (NetworkUtils.isConnectionOn(context))
            chain.proceed(chain.request())
        else
            throw InternetConnectionException()
}