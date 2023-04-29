package com.katherine.common.data.remote.interceptor

import com.katherine.common.data.remote.Api
import com.katherine.common.data.remote.exceptions.AuthException
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code == Api.STATUS_UNAUTHORIZED)
            throw AuthException()
        else
            return response
    }
}