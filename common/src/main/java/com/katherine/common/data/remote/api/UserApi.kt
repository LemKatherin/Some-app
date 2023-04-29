package com.katherine.common.data.remote.api

import com.katherine.common.data.remote.Api
import com.katherine.common.data.remote.model.AccessToken
import com.katherine.common.data.remote.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST(Api.LOGIN_METHOD)
    suspend fun login(@Body user: User): AccessToken
}