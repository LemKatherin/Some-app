package com.katherine.letstryagain.mvvm.viewModel.login

class UserCredentialsValidation(var isUsernameValid: Boolean, var isPasswordValid: Boolean) {
    fun isValid() = isUsernameValid && isPasswordValid
}