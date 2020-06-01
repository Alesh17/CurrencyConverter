package com.alesh.currencyconverter.ui.currencies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alesh.currencyconverter.ui.currencies.adapter.model.VoCurrency
import com.alesh.currencyconverter.ui.currencies.mapper.mapToVoCurrenciesList
import com.alesh.currencyconverter.util.livedata.Event
import com.alesh.domain.error.ApplicationErrors
import com.alesh.domain.interactor.CurrenciesInteractor
import com.alesh.domain.model.result.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrenciesViewModel @Inject constructor(
    private val interactor: CurrenciesInteractor
) : ViewModel() {

    val currencies = MutableLiveData<Event<List<VoCurrency>>>()
    val error = MutableLiveData<Event<ApplicationErrors>>()

    fun getCurrencies() {
        viewModelScope.launch {
            when (val result = interactor.getCurrencies()) {
                is Result.Success -> currencies.postValue(Event(result.value.mapToVoCurrenciesList()))
                is Result.Error   -> error.postValue(Event(result.error))
            }
        }
    }

    fun calculate(currentCurrency: VoCurrency, currentValue: String) {

        val value = currentValue.toDoubleOrNull() ?: 0.0
        val byn = value * currentCurrency.rate
        val list = currencies.value?.peekContent()?.toMutableList() ?: mutableListOf()

        list.forEachIndexed { position, item ->
            if (currentCurrency.id != item.id) {
                val value1 = byn / list[position].rate
                list[position].value = value1.toString() // попробовать item
            }
            //else list[position].value = currentCurrency.value
        }
    }
}