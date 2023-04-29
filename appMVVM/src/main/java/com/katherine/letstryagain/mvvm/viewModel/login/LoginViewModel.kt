package com.katherine.letstryagain.mvvm.viewModel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.katherine.common.R
import com.katherine.letstryagain.base.architecture.BaseViewModel
import com.katherine.letstryagain.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private val userCredentialsValidation = UserCredentialsValidation(
        isUsernameValid = false,
        isPasswordValid = false
    )

    private val _loginState = MutableLiveData<LoginState>(LoginState.Empty)
    val loginState: LiveData<LoginState> = _loginState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Authorizing
            userRepository.login(username, password)
                .process(onSuccess = { accessToken ->
                    val token = accessToken.value
                    if (token.isNotEmpty()) {
                        userRepository.saveUser(username, password, token)
                            .onSuccess {
                                _loginState.value = LoginState.Authorized
                            }
                            .onFailure {
                                _loginState.value = LoginState.AuthError(R.string.error)
                            }
                    } else
                        _loginState.value = LoginState.AuthError(R.string.error)
                }, onFailure = {
                    _loginState.value = LoginState.AuthError(it.messageResId)
                })
        }
    }

    fun usernameChanged(username: String) {
        userCredentialsValidation.isUsernameValid = isUsernameValid(username)
        _loginState.value = if (userCredentialsValidation.isValid())
            LoginState.Input.Validated
        else if (userCredentialsValidation.isUsernameValid)
            LoginState.Input.Username.Valid
        else
            LoginState.Input.Username.Error
    }

    fun passwordChanged(password: String) {
        userCredentialsValidation.isPasswordValid = isPasswordValid(password)
        _loginState.value = if (userCredentialsValidation.isValid())
            LoginState.Input.Validated
        else if (userCredentialsValidation.isPasswordValid)
            LoginState.Input.Password.Valid
        else
            LoginState.Input.Password.Error
    }

    private fun isUsernameValid(username: String) = username.isNotEmpty()

    private fun isPasswordValid(password: String) = password.isNotEmpty()
}