package com.katherine.letstryagain.mvi.viewModel.main

import com.katherine.letstryagain.base.architecture.BaseViewModel
import com.katherine.letstryagain.mvi.action.main.MainAction
import com.katherine.letstryagain.mvi.intent.main.MainIntent
import com.katherine.letstryagain.mvi.viewState.main.MainState

class MainViewModel : BaseViewModel<MainState, MainIntent, MainAction>() {
    override fun intentToAction(intent: MainIntent): MainAction {
        TODO("Not yet implemented")
    }

    override fun handleAction(action: MainAction) {
        TODO("Not yet implemented")
    }
}