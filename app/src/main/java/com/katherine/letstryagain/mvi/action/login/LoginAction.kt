package com.katherine.letstryagain.mvi.action.login

import com.katherine.letstryagain.base.architecture.ViewAction

sealed class LoginAction : ViewAction {
    data class Login(val username: String, val password: String) : LoginAction()

    data class CheckUsername(val username: String) : LoginAction()

    data class CheckPassword(val password: String) : LoginAction()
}