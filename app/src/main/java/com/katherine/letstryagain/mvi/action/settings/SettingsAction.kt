package com.katherine.letstryagain.mvi.action.settings

import com.katherine.letstryagain.base.architecture.ViewAction

sealed class SettingsAction : ViewAction {
    object LoadInitState : SettingsAction()
    data class UpdateTestFeature(val isTurnedOn: Boolean) : SettingsAction()
}