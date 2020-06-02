package com.alesh.currencyconverter.ui.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.alesh.currencyconverter.BR

class VoCurrency : BaseObservable() {

    var id: Int = 0
    var scale: Int = 1
    var rate: Double = 1.0
    var abbreviation: String = ""
    var isFavorite: Boolean = false

    @get:Bindable
    var value: String = "0.0"

    fun setValueWithNotify(v: Double) {
        value = v.toString()
        notifyPropertyChanged(BR.value)
    }
}