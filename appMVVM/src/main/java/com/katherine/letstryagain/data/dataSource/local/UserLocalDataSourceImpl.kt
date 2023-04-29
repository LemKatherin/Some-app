package com.katherine.letstryagain.data.dataSource.local

import android.accounts.AccountManager
import com.katherine.letstryagain.data.account.AccountUtils

class UserLocalDataSourceImpl(
    private val accountManager: AccountManager
) : UserLocalDataSource {
    override suspend fun saveUser(username: String, password: String, token: String) =
        AccountUtils.addAccount(accountManager, username, password, token)
}