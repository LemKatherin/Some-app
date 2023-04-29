package com.katherine.letstryagain.data.account

import android.accounts.Account
import android.accounts.AccountManager
import com.katherine.letstryagain.BuildConfig
import kotlinx.coroutines.Dispatchers

object AccountUtils {

    private const val ACCOUNT_TYPE = BuildConfig.APPLICATION_ID
    private const val AUTH_TOKEN_TYPE = "${BuildConfig.APPLICATION_ID}.token"

    const val ADD_NEW_ACCOUNT = "add_new_account"

    fun getAuthToken(accountManager: AccountManager): String {
        val account = accountManager.getAccountsByType(ACCOUNT_TYPE).getOrNull(0)
        return if (account != null) accountManager.peekAuthToken(account, AUTH_TOKEN_TYPE)
            ?: "" else ""
    }

    suspend fun addAccount(
        accountManager: AccountManager,
        username: String,
        password: String,
        token: String
    ): Result<Unit> = com.katherine.common.utils.withContextCatching(Dispatchers.Default) {
        val account = Account(username, ACCOUNT_TYPE)
        accountManager.addAccountExplicitly(account, password, null)
        with(accountManager) {
            setAuthToken(
                account,
                AUTH_TOKEN_TYPE,
                token
            )
        }
    }
}