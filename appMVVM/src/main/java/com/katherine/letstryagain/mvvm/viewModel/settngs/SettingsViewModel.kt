package com.katherine.letstryagain.mvvm.viewModel.settngs

import androidx.lifecycle.viewModelScope
import com.katherine.common.data.preferences.PreferencesApi
import com.katherine.letstryagain.base.architecture.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesApi: PreferencesApi
) : BaseViewModel() {

    val testFeatureFlow: Flow<Boolean> = preferencesApi.getTestFeature()

    fun updateTestFeature(isTurnedOn: Boolean) {
        viewModelScope.launch {
            preferencesApi.saveTestFeature(isTurnedOn)
        }
    }
}