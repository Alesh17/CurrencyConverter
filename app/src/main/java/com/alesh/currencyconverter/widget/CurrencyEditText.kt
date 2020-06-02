package com.alesh.currencyconverter.widget

import android.content.Context
import android.util.AttributeSet
import com.alesh.currencyconverter.util.NumberTextWatcherForThousand

class CurrencyEditText(context: Context, attrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatEditText(context, attrs) {

    init {
        this.addTextChangedListener(NumberTextWatcherForThousand(this))
    }
}