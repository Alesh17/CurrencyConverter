package com.alesh.currencyconverter.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.alesh.currencyconverter.util.CurrencyTextWatcher
import com.alesh.currencyconverter.util.system.getCurrentSeparators

class CurrencyEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    private val separators by lazy { context.getCurrentSeparators() }

    init {
        this.addTextChangedListener(CurrencyTextWatcher(this, separators))
    }
}