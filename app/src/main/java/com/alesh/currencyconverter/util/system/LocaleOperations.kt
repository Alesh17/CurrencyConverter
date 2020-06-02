@file:Suppress("DEPRECATION")

package com.alesh.currencyconverter.util.system

import android.content.Context
import android.os.Build
import com.alesh.currencyconverter.widget.Separators
import java.util.*

private fun Context.getCurrentLocale(): Locale {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        this.resources.configuration.locales.get(0)
    else
        this.resources.configuration.locale
}

fun Context.getCurrentSeparators() =
    when (this.getCurrentLocale().country) {
        "US" -> Separators.US
        "RU" -> Separators.RU
        else -> Separators.US
    }