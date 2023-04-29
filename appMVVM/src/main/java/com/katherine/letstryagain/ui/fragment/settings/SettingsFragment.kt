package com.katherine.letstryagain.ui.fragment.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.katherine.letstryagain.R
import com.katherine.letstryagain.base.ui.BaseFragment
import com.katherine.letstryagain.databinding.FragmentSettingsBinding
import com.katherine.letstryagain.mvvm.viewModel.settngs.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment :
    BaseFragment<FragmentSettingsBinding, SettingsViewModel>(
        R.layout.fragment_settings
    ) {
    override val viewModel: SettingsViewModel by viewModels()
    override val binding: FragmentSettingsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.chkTestFeature.isChecked =
                viewModel.testFeatureFlow.flowWithLifecycle(viewLifecycleOwner.lifecycle).first()
            binding.chkTestFeature.setOnCheckedChangeListener { _, isChecked ->
                viewModel.updateTestFeature(isChecked)
            }
        }
    }
}