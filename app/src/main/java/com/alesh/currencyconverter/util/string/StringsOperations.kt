package com.alesh.currencyconverter.util.string

import android.content.Context
import com.alesh.currencyconverter.util.system.getCurrentSeparators

fun String.fixForDoubleUsage(context: Context): String {
    val separators = context.applicationContext.getCurrentSeparators()
    return replace(separators.first, "").replace(separators.second, ".")
}

fun String.fixForDoubleUsage(separators: Pair<String, String>): String {
    return replace(separators.first, "").replace(separators.second, ".")
}