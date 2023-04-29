package com.katherine.letstryagain.ui.fragment.settings

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.katherine.letstryagain.R
import com.katherine.letstryagain.base.ui.BaseFragment
import com.katherine.letstryagain.databinding.FragmentSettingsBinding
import com.katherine.letstryagain.mvi.action.settings.SettingsAction
import com.katherine.letstryagain.mvi.intent.settings.SettingsIntent
import com.katherine.letstryagain.mvi.viewModel.settngs.SettingsViewModel
import com.katherine.letstryagain.mvi.viewState.settings.SettingsState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment :
    BaseFragment<FragmentSettingsBinding, SettingsState, SettingsIntent, SettingsAction, SettingsViewModel>(
        R.layout.fragment_settings
    ) {
    override val viewModel: SettingsViewModel by viewModel()
    override val binding: FragmentSettingsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dispatchIntent(SettingsIntent.LoadInitState)
    }

    override fun render(state: SettingsState) {
        when (state) {
            is SettingsState.InitState -> {
                binding.chkTestFeature.apply {
                    isChecked = state.isTestFeatureTurnedOn
                    setOnCheckedChangeListener { _, isChecked ->
                        viewModel.dispatchIntent(SettingsIntent.UpdateTestFeature(isChecked))
                    }
                }
            }
        }
    }
}