package com.katherine.letstryagain.base.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.katherine.letstryagain.base.architecture.BaseView
import com.katherine.letstryagain.base.architecture.BaseViewModel

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    @LayoutRes layoutRes: Int
) : Fragment(layoutRes), BaseView<VB, VM>