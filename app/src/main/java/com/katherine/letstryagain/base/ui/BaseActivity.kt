package com.katherine.letstryagain.base.ui

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.katherine.letstryagain.base.architecture.*

abstract class BaseActivity<VB : ViewBinding, STATE : ViewState, INTENT : ViewIntent, ACTION : ViewAction, VM : BaseViewModel<STATE, INTENT, ACTION>>(
    @LayoutRes layoutRes: Int
) :
    AppCompatActivity(layoutRes), BaseView<VB, STATE, INTENT, ACTION, VM> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeOnState(this)
    }

    fun showToast(@StringRes messageResId: Int) {
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
    }
}