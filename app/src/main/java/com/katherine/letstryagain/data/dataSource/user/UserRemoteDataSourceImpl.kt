package com.katherine.letstryagain.data.dataSource.user

import com.katherine.common.data.remote.api.UserApi
import com.katherine.common.data.remote.model.User
import com.katherine.common.data.remote.responseWrapper.ApiCall

class UserRemoteDataSourceImpl(
    private val apiCall: ApiCall,
    private val userApi: UserApi
) : UserRemoteDataSource {
    override suspend fun login(username: String, password: String) =
        apiCall.executeApiCall { userApi.login(User(username, password)) }
}