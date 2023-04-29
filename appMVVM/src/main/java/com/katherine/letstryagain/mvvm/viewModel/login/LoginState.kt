package com.katherine.letstryagain.mvvm.viewModel.login

import androidx.annotation.StringRes

sealed class LoginState {

    object Empty : LoginState()

    sealed class Input : LoginState() {
        sealed class Password : LoginState() {
            object Valid : Password()
            object Error : Password()
        }

        sealed class Username : LoginState() {
            object Valid : Username()
            object Error : Username()
        }

        object Validated : LoginState()
    }

    object Authorizing : LoginState()

    object Authorized : LoginState()

    data class AuthError(@StringRes val messageResId: Int) : LoginState()
}