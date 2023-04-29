package com.katherine.letstryagain.data.repository.user

import com.katherine.common.data.remote.model.AccessToken
import com.katherine.common.data.remote.responseWrapper.ApiResult

interface UserRepository {
    suspend fun saveUser(username: String, password: String, token: String): Result<Unit>
    suspend fun login(username: String, password: String): ApiResult<AccessToken>
}