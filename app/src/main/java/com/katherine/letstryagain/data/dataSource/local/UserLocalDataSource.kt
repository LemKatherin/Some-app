package com.katherine.letstryagain.data.dataSource.local

interface UserLocalDataSource {
    suspend fun saveUser(username: String, password: String, token: String): Result<Unit>
}