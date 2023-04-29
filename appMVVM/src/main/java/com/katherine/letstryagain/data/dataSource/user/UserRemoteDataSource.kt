package com.katherine.letstryagain.data.dataSource.user

import com.katherine.common.data.remote.model.AccessToken
import com.katherine.common.data.remote.responseWrapper.ApiResult

interface UserRemoteDataSource {
    suspend fun login(username: String, password: String): ApiResult<AccessToken>
}