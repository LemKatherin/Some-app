package com.katherine.letstryagain.ui.activity.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import by.kirich1409.viewbindingdelegate.viewBinding
import com.katherine.common.utils.view.afterTextChanged
import com.katherine.common.utils.view.getTextValue
import com.katherine.common.utils.view.setSafeOnClickListener
import com.katherine.common.utils.view.setVisible
import com.katherine.letstryagain.R
import com.katherine.letstryagain.base.ui.BaseActivity
import com.katherine.letstryagain.databinding.ActivityLoginBinding
import com.katherine.letstryagain.mvvm.viewModel.login.LoginState
import com.katherine.letstryagain.mvvm.viewModel.login.LoginViewModel
import com.katherine.letstryagain.ui.activity.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity :
    BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {
    override val viewModel: LoginViewModel by viewModels()

    override val binding: ActivityLoginBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        with(binding) {
            btnLogin.setSafeOnClickListener {
                viewModel.login(
                    etUsername.getTextValue(),
                    etPassword.getTextValue()
                )
            }
            etUsername.afterTextChanged {
                viewModel.usernameChanged(it)
            }
            etPassword.afterTextChanged {
                viewModel.passwordChanged(it)
            }
        }
    }

    private fun initObservers() {
        viewModel.loginState.observe(this) { loginState ->
            binding.btnLogin.isEnabled =
                loginState == LoginState.Input.Validated || loginState is LoginState.AuthError
            binding.loading.setVisible(loginState is LoginState.Authorizing)
            when (loginState) {
                LoginState.Input.Username.Error -> {
                    binding.tilUsername.error = getString(R.string.error_username)
                }
                LoginState.Input.Username.Valid -> {
                    binding.tilUsername.error = null
                }
                LoginState.Input.Password.Error -> {
                    binding.tilPassword.error = getString(R.string.error_password)
                }
                LoginState.Input.Password.Valid -> {
                    binding.tilPassword.error = null
                }
                LoginState.Input.Validated -> {
                    binding.tilUsername.error = null
                    binding.tilPassword.error = null
                }
                LoginState.Empty -> {
                    binding.tilUsername.error = null
                    binding.tilPassword.error = null
                }
                LoginState.Authorized -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is LoginState.AuthError -> {
                    showToast(loginState.messageResId)
                }
                else -> {

                }
            }
        }
    }
}