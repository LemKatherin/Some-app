package com.katherine.common.data.remote.responseWrapper

import com.katherine.common.data.remote.exceptions.AuthException
import com.katherine.common.data.remote.exceptions.InternetConnectionException

class ApiCallImpl : ApiCall {
    override suspend fun <T> executeApiCall(action: suspend () -> T): ApiResult<T> =
        kotlin.runCatching {
            ApiResult.Success(action.invoke())
        }.getOrElse {
            handleError(it)
        }

    override suspend fun handleError(throwable: Throwable): ApiResult.Failure = when (throwable) {
        is InternetConnectionException -> ApiResult.Failure(ApiError.ConnectionError)
        is AuthException -> ApiResult.Failure(ApiError.AuthError)
        else -> ApiResult.Failure(ApiError.SomeError)
    }

}