package com.katherine.letstryagain.mvi.intent.login

import com.katherine.letstryagain.base.architecture.ViewIntent

sealed class LoginIntent : ViewIntent {
    data class Login(val username: String, val password: String) : LoginIntent()

    data class InputUsername(val username: String) : LoginIntent()

    data class InputPassword(val password: String) : LoginIntent()
}