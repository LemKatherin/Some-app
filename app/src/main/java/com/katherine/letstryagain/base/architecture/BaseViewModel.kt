package com.katherine.letstryagain.base.architecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE : ViewState, INTENT : ViewIntent, ACTION : ViewAction> :
    ViewModel() {

    private val _viewState: MutableLiveData<STATE> = MutableLiveData()
    val viewState: LiveData<STATE> = _viewState

    fun setViewState(state: STATE) {
        viewModelScope.launch {
            _viewState.value = state
        }
    }

    fun dispatchIntent(intent: INTENT) = handleAction(intentToAction(intent))

    abstract fun intentToAction(intent: INTENT): ACTION
    abstract fun handleAction(action: ACTION)
}