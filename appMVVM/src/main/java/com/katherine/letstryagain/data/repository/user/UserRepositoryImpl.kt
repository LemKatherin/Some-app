package com.katherine.letstryagain.data.repository.user

import com.katherine.letstryagain.data.dataSource.local.UserLocalDataSource
import com.katherine.letstryagain.data.dataSource.user.UserRemoteDataSource

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {
    override suspend fun login(username: String, password: String) =
        userRemoteDataSource.login(username, password)

    override suspend fun saveUser(username: String, password: String, token: String) =
        userLocalDataSource.saveUser(username, password, token)
}