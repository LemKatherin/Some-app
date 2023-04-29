package com.katherine.letstryagain.data.account

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.katherine.common.data.remote.model.AccessToken
import com.katherine.common.data.remote.responseWrapper.ApiResult
import com.katherine.letstryagain.data.dataSource.user.UserRemoteDataSource
import com.katherine.letstryagain.ui.activity.login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountAuthenticator(
    private val context: Context,
    private val userRemoteDataSource: UserRemoteDataSource
) : AbstractAccountAuthenticator(context) {
    override fun addAccount(
        response: AccountAuthenticatorResponse?,
        accountType: String?,
        authTokenType: String?,
        requiredFeatures: Array<out String>?,
        options: Bundle?
    ): Bundle {
        val intent = Intent(context, LoginActivity::class.java)
            .apply {
                putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType)
                putExtra(AccountUtils.ADD_NEW_ACCOUNT, true)
                putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
            }
        val bundle = Bundle()
        if (options != null) {
            bundle.putAll(options)
        }
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)
        return bundle
    }

    suspend fun getAuthTokenAsync(
        response: AccountAuthenticatorResponse?,
        account: Account?
    ): Bundle = withContext(Dispatchers.Default) {
        if (account != null) {
            val accountManager = AccountManager.get(context.applicationContext)
            val username = account.name
            val password = accountManager.getPassword(account)
            val newToken = when (val result = userRemoteDataSource.login(username, password)) {
                is ApiResult.Success<AccessToken> -> {
                    val token = result.value.value
                    AccountUtils.addAccount(accountManager, username, password, token)
                    token
                }
                is ApiResult.Failure -> {
                    null
                }
            }
            createResult(newToken, username, account, response)
        } else {
            createResult(null, null, null, response)
        }
    }

    override fun getAuthToken(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle {
        val accountManager = AccountManager.get(context.applicationContext)
        val authToken = accountManager.peekAuthToken(account, authTokenType)
        val username = account?.name

        return createResult(authToken, username, account, response)
    }

    private fun createResult(
        authToken: String?,
        username: String?,
        account: Account?,
        response: AccountAuthenticatorResponse?
    ): Bundle {
        val result = Bundle()
        if (!authToken.isNullOrEmpty()) {
            with(result) {
                putString(AccountManager.KEY_ACCOUNT_NAME, username)
                putString(AccountManager.KEY_ACCOUNT_TYPE, account?.type)
                putString(AccountManager.KEY_AUTHTOKEN, authToken)
            }
        } else {
            val intent = Intent(context, LoginActivity::class.java)
                .apply {
                    putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
                }
            result.putParcelable(AccountManager.KEY_INTENT, intent)
        }
        return result
    }

    override fun getAuthTokenLabel(authTokenType: String?): String? {
        return null
    }

    override fun updateCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle? {
        return null
    }

    override fun hasFeatures(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        features: Array<out String>?
    ): Bundle? {
        return null
    }

    override fun editProperties(
        response: AccountAuthenticatorResponse?,
        accountType: String?
    ): Bundle? {
        return null
    }

    override fun confirmCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        options: Bundle?
    ): Bundle? {
        return null
    }
}