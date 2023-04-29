package com.katherine.common.data.remote.responseWrapper

sealed class ApiResult<out T> {
    class Success<out T>(val value: T) : ApiResult<T>()
    class Failure(val apiError: ApiError) : ApiResult<Nothing>()

    suspend fun process(
        onSuccess: suspend (value: T) -> Unit,
        onFailure: suspend (apiError: ApiError) -> Unit
    ) {
        when (this) {
            is Success -> {
                onSuccess(value)
            }
            is Failure -> {
                onFailure(apiError)
            }
        }
    }
}
