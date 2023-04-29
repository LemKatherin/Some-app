package com.katherine.common.utils.view

import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toInVisible() {
    this.visibility = View.INVISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.setVisible(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}

fun EditText.afterTextChanged(action: (String) -> Unit) = doAfterTextChanged {
    action(it?.toString() ?: "")
}

fun EditText.getTextValue(): String = text?.toString() ?: ""