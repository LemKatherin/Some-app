package com.katherine.letstryagain.ui.activity.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.katherine.letstryagain.R
import com.katherine.letstryagain.base.ui.BaseActivity
import com.katherine.letstryagain.databinding.ActivityMainBinding
import com.katherine.letstryagain.mvi.action.main.MainAction
import com.katherine.letstryagain.mvi.intent.main.MainIntent
import com.katherine.letstryagain.mvi.viewModel.main.MainViewModel
import com.katherine.letstryagain.mvi.viewState.main.MainState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity :
    BaseActivity<ActivityMainBinding, MainState, MainIntent, MainAction, MainViewModel>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModel()

    override val binding: ActivityMainBinding by viewBinding()

    private val navController by lazy { findNavController(R.id.nav_host_fragment_activity_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.appBarMain.toolbar)
        val navView: BottomNavigationView = binding.navView

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_notes, R.id.navigation_settings)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun render(state: MainState) {
        TODO("Not yet implemented")
    }
}