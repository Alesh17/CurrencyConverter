package com.alesh.currencyconverter.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.alesh.currencyconverter.util.string.fixForDoubleUsage
import java.util.*

class CurrencyTextWatcher(
    private val editText: EditText,
    private val separators: Pair<String, String>
) : TextWatcher {

    private val separatorForThousand = separators.first
    private val separatorForFractional = separators.second

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(s: Editable?) {
        try {
            val value = editText.text.toString()
            editText.removeTextChangedListener(this)
            if (value.isNotEmpty()) {

                val text = getDecimalFormattedString(value.fixForDoubleUsage(separators))
                    .replace(".", separatorForFractional)

                editText.apply {
                    setText(text)
                    setSelection(editText.text.toString().length)
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            editText.addTextChangedListener(this)
        }
    }

    private fun getDecimalFormattedString(value: String): String {

        val lst = StringTokenizer(value, ".")
        var str1 = value
        var str2 = ""
        if (lst.countTokens() > 1) {
            str1 = lst.nextToken()
            str2 = lst.nextToken()
        }
        var str3 = ""
        var i = 0
        var j = -1 + str1.length
        if (str1[-1 + str1.length] == '.') {
            j--
            str3 = "."
        }
        var k = j
        while (true) {
            if (k < 0) {
                if (str2.isNotEmpty()) str3 = "$str3.$str2"
                return str3
            }
            if (i == 3) {
                str3 = "$separatorForThousand$str3"
                i = 0
            }
            str3 = str1[k].toString() + str3
            i++
            k--
        }
    }
}