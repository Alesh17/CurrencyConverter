@file:SuppressLint("WrongConstant")

package com.alesh.currencyconverter.util.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

private const val snackbarDuration: Int = 4000

fun Context.snackbar(view: View, @StringRes messageStringRes: Int) {

    hideKeyboard(view)

    if (view.isAttachedToWindow) {
        val color = com.alesh.currencyconverter.R.color.colorError
        val actionColor = android.R.color.white
        val snackbar = Snackbar.make(view, getString(messageStringRes), Snackbar.LENGTH_INDEFINITE)
            .setDuration(snackbarDuration)
            .setActionTextColor(ContextCompat.getColor(applicationContext, actionColor))
            .setAction(getString(com.alesh.currencyconverter.R.string.close_caps)) { }
        snackbar.view.setBackgroundColor(ContextCompat.getColor(applicationContext, color))
        snackbar.show()
    }
}