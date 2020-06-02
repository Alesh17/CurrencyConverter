package com.alesh.currencyconverter.util

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import java.util.*

class NumberTextWatcherForThousand(private val editText: EditText) : TextWatcher {

    companion object {
        val separatorForFractional = ","
        val separatorForThousand = " "
        fun String.trimCommaOfString(): String {
            var normalString = this
            normalString = normalString.replace(separatorForFractional, ".")
            normalString = normalString.replace(separatorForThousand, "")
            return normalString
//            return if (this.contains(separatorForThousand)) this.replace(separatorForThousand, "")
//            else this
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        try {
            editText.removeTextChangedListener(this)
            //Log.i("alesh! пришло", s.toString())
            val value = editText.text.toString()

            if (value.isNotEmpty()) {

//                if (value.startsWith(".")) editText.setText("0.")
//                if (value.startsWith("0") && !value.startsWith("0.")) editText.setText("")

                var str = editText.text.toString().replace(separatorForThousand.toRegex(), "")
                str = str.replace(".", separatorForFractional)

                if (value != "") editText.setText(getDecimalFormattedString(str))
                editText.setSelection(editText.text.toString().length)
            }
            //Log.i("alesh! ушло", editText.text.toString())
            editText.addTextChangedListener(this)
            return
        } catch (ex: Exception) {
            ex.printStackTrace()
            editText.addTextChangedListener(this)
        }
    }

    private fun getDecimalFormattedString(value: String): String? {

        val lst = StringTokenizer(value, separatorForFractional)
        var str1 = value
        var str2 = ""
        if (lst.countTokens() > 1) {
            str1 = lst.nextToken()
            str2 = lst.nextToken()
        }
        var str3 = ""
        var i = 0
        var j = -1 + str1.length
        if (str1[-1 + str1.length] == separatorForFractional[0]) {
            j--
            str3 = separatorForFractional
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