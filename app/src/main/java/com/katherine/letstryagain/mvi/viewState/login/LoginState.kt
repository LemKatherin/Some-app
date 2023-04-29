package com.katherine.letstryagain.mvi.viewState.login

import com.katherine.letstryagain.base.architecture.ViewState

sealed class LoginState : ViewState {
    object Empty : LoginState()

    data class Input(val isUsernameError: Boolean?, val isPasswordError: Boolean?) : LoginState() {
        fun validated() =
            if (isUsernameError != null && isPasswordError != null) !isPasswordError && !isUsernameError else false
    }

    object Authorizing : LoginState()

    object Authorized : LoginState()

    data class AuthError(val messageResId: Int, val titleResId: Int? = null) : LoginState()
}