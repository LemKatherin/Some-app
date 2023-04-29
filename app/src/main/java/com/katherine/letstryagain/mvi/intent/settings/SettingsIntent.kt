package com.katherine.letstryagain.mvi.intent.settings

import com.katherine.letstryagain.base.architecture.ViewIntent

sealed class SettingsIntent : ViewIntent {
    object LoadInitState : SettingsIntent()
    data class UpdateTestFeature(val isTurnedOn: Boolean) : SettingsIntent()
}