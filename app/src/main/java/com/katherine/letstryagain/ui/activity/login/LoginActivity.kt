package com.katherine.letstryagain.ui.activity.login

import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import by.kirich1409.viewbindingdelegate.viewBinding
import com.katherine.common.utils.view.afterTextChanged
import com.katherine.common.utils.view.getTextValue
import com.katherine.common.utils.view.setSafeOnClickListener
import com.katherine.common.utils.view.setVisible
import com.katherine.letstryagain.R
import com.katherine.letstryagain.base.ui.BaseActivity
import com.katherine.letstryagain.databinding.ActivityLoginBinding
import com.katherine.letstryagain.mvi.action.login.LoginAction
import com.katherine.letstryagain.mvi.intent.login.LoginIntent
import com.katherine.letstryagain.mvi.viewModel.login.LoginViewModel
import com.katherine.letstryagain.mvi.viewState.login.LoginState
import com.katherine.letstryagain.ui.activity.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity :
    BaseActivity<ActivityLoginBinding, LoginState, LoginIntent, LoginAction, LoginViewModel>(R.layout.activity_login) {
    override val viewModel: LoginViewModel by viewModel()

    override val binding: ActivityLoginBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            btnLogin.setSafeOnClickListener {
                viewModel.dispatchIntent(
                    LoginIntent.Login(
                        etUsername.getTextValue(),
                        etPassword.getTextValue()
                    )
                )
            }
            etUsername.afterTextChanged {
                viewModel.dispatchIntent(LoginIntent.InputUsername(it))
            }
            etPassword.afterTextChanged {
                viewModel.dispatchIntent(LoginIntent.InputPassword(it))
            }
        }
    }

    override fun render(state: LoginState) {
        binding.btnLogin.isEnabled =
            (state is LoginState.Input && state.validated()) || state is LoginState.AuthError
        binding.loading.setVisible(state is LoginState.Authorizing)
        when (state) {
            is LoginState.AuthError -> showToast(state.messageResId)
            LoginState.Authorized -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            LoginState.Empty -> {
                binding.tilUsername.error = null
                binding.tilPassword.error = null
            }
            is LoginState.Input -> {
                if (state.isUsernameError == true)
                    binding.tilUsername.error = getString(R.string.error_username)
                else
                    binding.tilUsername.error = null
                if (state.isPasswordError == true)
                    binding.tilPassword.error = getString(R.string.error_password)
                else
                    binding.tilPassword.error = null
            }
            else -> {

            }
        }
    }
}