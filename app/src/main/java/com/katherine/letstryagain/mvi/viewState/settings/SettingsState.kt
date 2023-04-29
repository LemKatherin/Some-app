package com.katherine.letstryagain.mvi.viewState.settings

import com.katherine.letstryagain.base.architecture.ViewState

sealed class SettingsState : ViewState {
    data class InitState(val isTestFeatureTurnedOn: Boolean) : SettingsState()
}