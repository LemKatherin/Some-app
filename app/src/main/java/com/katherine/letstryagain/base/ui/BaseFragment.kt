package com.katherine.letstryagain.base.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.katherine.letstryagain.base.architecture.*

abstract class BaseFragment<VB : ViewBinding, STATE : ViewState, INTENT : ViewIntent, ACTION : ViewAction, VM : BaseViewModel<STATE, INTENT, ACTION>>(
    @LayoutRes layoutRes: Int
) :
    Fragment(layoutRes), BaseView<VB, STATE, INTENT, ACTION, VM> {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeOnState(viewLifecycleOwner)
    }
}