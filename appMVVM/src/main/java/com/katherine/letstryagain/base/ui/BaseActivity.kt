package com.katherine.letstryagain.base.ui

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.katherine.letstryagain.base.architecture.BaseView
import com.katherine.letstryagain.base.architecture.BaseViewModel

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel>(
    @LayoutRes layoutRes: Int
) :
    AppCompatActivity(layoutRes), BaseView<VB, VM> {

    fun showToast(@StringRes messageResId: Int) {
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
    }
}