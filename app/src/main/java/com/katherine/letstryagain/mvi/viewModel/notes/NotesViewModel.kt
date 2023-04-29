package com.katherine.letstryagain.mvi.viewModel.notes

import com.katherine.letstryagain.base.architecture.BaseViewModel
import com.katherine.letstryagain.mvi.action.notes.NotesAction
import com.katherine.letstryagain.mvi.intent.notes.NotesIntent
import com.katherine.letstryagain.mvi.viewState.notes.NotesState

class NotesViewModel : BaseViewModel<NotesState, NotesIntent, NotesAction>() {
    override fun intentToAction(intent: NotesIntent): NotesAction {
        TODO("Not yet implemented")
    }

    override fun handleAction(action: NotesAction) {
        TODO("Not yet implemented")
    }
}