package com.katherine.common.utils.view

import android.view.View

class SafeClickListener(private val onSafeCLick: (View) -> Unit) : View.OnClickListener {
    private var lastClickTime = 0L
    private val interval = 700L
    override fun onClick(v: View) {
        if (System.currentTimeMillis() - lastClickTime < interval)
            return
        lastClickTime = System.currentTimeMillis()
        onSafeCLick(v)
    }
}