package com.katherine.letstryagain.mvi.viewModel.login

import androidx.lifecycle.viewModelScope
import com.katherine.common.R
import com.katherine.letstryagain.base.architecture.BaseViewModel
import com.katherine.letstryagain.data.repository.user.UserRepository
import com.katherine.letstryagain.mvi.action.login.LoginAction
import com.katherine.letstryagain.mvi.intent.login.LoginIntent
import com.katherine.letstryagain.mvi.viewState.login.LoginState
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) :
    BaseViewModel<LoginState, LoginIntent, LoginAction>() {

    override fun intentToAction(intent: LoginIntent): LoginAction = when (intent) {
        is LoginIntent.Login -> LoginAction.Login(intent.username, intent.password)
        is LoginIntent.InputUsername -> LoginAction.CheckUsername(intent.username)
        is LoginIntent.InputPassword -> LoginAction.CheckPassword(intent.password)
    }

    override fun handleAction(action: LoginAction) {
        viewModelScope.launch {
            when (action) {
                is LoginAction.Login -> {
                    setViewState(LoginState.Authorizing)
                    userRepository.login(action.username, action.password)
                        .process(onSuccess = { accessToken ->
                            val token = accessToken.value
                            if (token.isNotEmpty()) {
                                userRepository.saveUser(action.username, action.password, token)
                                    .onSuccess {
                                        setViewState(LoginState.Authorized)
                                    }
                                    .onFailure {
                                        setViewState(LoginState.AuthError(R.string.error))
                                    }
                            } else setViewState(LoginState.AuthError(R.string.error))
                        }, onFailure = {
                            setViewState(LoginState.AuthError(it.messageResId))
                        })
                }
                is LoginAction.CheckUsername -> {
                    setViewState(
                        LoginState.Input(
                            invalidUsername(action.username),
                            (viewState.value as? LoginState.Input)?.isPasswordError
                        )
                    )
                }
                is LoginAction.CheckPassword -> {
                    setViewState(
                        LoginState.Input(
                            (viewState.value as? LoginState.Input)?.isUsernameError,
                            invalidPassword(action.password)
                        )
                    )
                }
            }
        }
    }

    private fun invalidUsername(username: String) = username.isEmpty()

    private fun invalidPassword(password: String) = password.isEmpty()
}