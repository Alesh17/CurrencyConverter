package com.alesh.currencyconverter.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText


class SuperEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    init {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //s is the current character in the eddittext after it is changed
            }
        })
    }
}