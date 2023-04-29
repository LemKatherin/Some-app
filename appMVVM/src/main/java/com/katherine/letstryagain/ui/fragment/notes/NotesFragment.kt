package com.katherine.letstryagain.ui.fragment.notes

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.katherine.letstryagain.R
import com.katherine.letstryagain.base.ui.BaseFragment
import com.katherine.letstryagain.databinding.FragmentNotesBinding
import com.katherine.letstryagain.mvvm.viewModel.notes.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment :
    BaseFragment<FragmentNotesBinding, NotesViewModel>(
        R.layout.fragment_notes
    ) {
    override val viewModel: NotesViewModel by viewModels()
    override val binding: FragmentNotesBinding by viewBinding()
}