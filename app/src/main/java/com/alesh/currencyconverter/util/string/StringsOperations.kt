package com.alesh.currencyconverter.util.string

import com.alesh.currencyconverter.util.NumberTextWatcherForThousand.Companion.separatorForFractional
import com.alesh.currencyconverter.util.NumberTextWatcherForThousand.Companion.separatorForThousand

fun String.fixCommasAndDotsOfString(): String {
    var normalString = this
    normalString = normalString.replace(separatorForFractional, ".")
    normalString = normalString.replace(separatorForThousand, "")
    return normalString
}