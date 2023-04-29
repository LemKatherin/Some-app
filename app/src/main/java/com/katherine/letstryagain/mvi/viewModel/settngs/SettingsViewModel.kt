package com.katherine.letstryagain.mvi.viewModel.settngs

import androidx.lifecycle.viewModelScope
import com.katherine.common.data.preferences.PreferencesApi
import com.katherine.letstryagain.base.architecture.BaseViewModel
import com.katherine.letstryagain.mvi.action.settings.SettingsAction
import com.katherine.letstryagain.mvi.intent.settings.SettingsIntent
import com.katherine.letstryagain.mvi.viewState.settings.SettingsState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SettingsViewModel(private val preferencesApi: PreferencesApi) :
    BaseViewModel<SettingsState, SettingsIntent, SettingsAction>() {

    override fun intentToAction(intent: SettingsIntent): SettingsAction = when (intent) {
        is SettingsIntent.LoadInitState -> SettingsAction.LoadInitState
        is SettingsIntent.UpdateTestFeature -> SettingsAction.UpdateTestFeature(intent.isTurnedOn)
    }

    override fun handleAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.LoadInitState -> {
                viewModelScope.launch {
                    setViewState(SettingsState.InitState(preferencesApi.getTestFeature().first()))
                }
            }
            is SettingsAction.UpdateTestFeature -> {
                viewModelScope.launch {
                    preferencesApi.saveTestFeature(action.isTurnedOn)
                }
            }
        }
    }
}