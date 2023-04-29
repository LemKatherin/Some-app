package com.katherine.common.data.remote.responseWrapper

import androidx.annotation.StringRes
import com.katherine.common.R

sealed class ApiError(@StringRes val messageResId: Int) {
    object SomeError : ApiError(R.string.error)
    object AuthError : ApiError(R.string.error_auth)
    object ConnectionError : ApiError(R.string.error_connection)
}