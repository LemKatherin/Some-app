package com.katherine.letstryagain.ui.fragment.notes

import by.kirich1409.viewbindingdelegate.viewBinding
import com.katherine.letstryagain.R
import com.katherine.letstryagain.base.ui.BaseFragment
import com.katherine.letstryagain.databinding.FragmentNotesBinding
import com.katherine.letstryagain.mvi.action.notes.NotesAction
import com.katherine.letstryagain.mvi.intent.notes.NotesIntent
import com.katherine.letstryagain.mvi.viewModel.notes.NotesViewModel
import com.katherine.letstryagain.mvi.viewState.notes.NotesState
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesFragment :
    BaseFragment<FragmentNotesBinding, NotesState, NotesIntent, NotesAction, NotesViewModel>(
        R.layout.fragment_notes
    ) {
    override val viewModel: NotesViewModel by viewModel()
    override val binding: FragmentNotesBinding by viewBinding()

    override fun render(state: NotesState) {

    }
}