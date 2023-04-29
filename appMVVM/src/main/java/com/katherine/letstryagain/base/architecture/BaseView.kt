package com.katherine.letstryagain.base.architecture

import androidx.viewbinding.ViewBinding

interface BaseView<VB : ViewBinding, VM : BaseViewModel> {
    val viewModel: VM
    val binding: VB
}