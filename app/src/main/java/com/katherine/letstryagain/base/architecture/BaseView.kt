package com.katherine.letstryagain.base.architecture

import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

interface BaseView<VB : ViewBinding, STATE : ViewState, INTENT : ViewIntent, ACTION : ViewAction, VM : BaseViewModel<STATE, INTENT, ACTION>> {
    val viewModel: VM
    val binding: VB

    fun render(state: STATE)

    fun subscribeOnState(lifecycleOwner: LifecycleOwner) {
        viewModel.viewState.observe(lifecycleOwner) { viewState ->
            render(viewState)
        }
    }
}