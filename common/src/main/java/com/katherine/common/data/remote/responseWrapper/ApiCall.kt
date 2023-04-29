package com.katherine.common.data.remote.responseWrapper

interface ApiCall {
    suspend fun <T> executeApiCall(action: suspend () -> T): ApiResult<T>
    suspend fun handleError(throwable: Throwable): ApiResult.Failure
}