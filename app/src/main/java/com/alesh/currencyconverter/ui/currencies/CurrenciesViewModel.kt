package com.alesh.currencyconverter.ui.currencies

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alesh.currencyconverter.ui.model.VoCurrency
import com.alesh.currencyconverter.ui.model.mapper.mapToVoCurrenciesList
import com.alesh.currencyconverter.util.livedata.Event
import com.alesh.currencyconverter.util.string.fixForDoubleUsage
import com.alesh.domain.error.ApplicationErrors
import com.alesh.domain.interactor.CurrenciesInteractor
import com.alesh.domain.model.result.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrenciesViewModel @Inject constructor(
    private val app: Application,
    private val interactor: CurrenciesInteractor
) : ViewModel() {

    val favoriteCurrencies = MutableLiveData<Event<List<VoCurrency>>>()
    val error = MutableLiveData<Event<ApplicationErrors>>()
    val isLoading = MutableLiveData<Event<Boolean>>()

    fun getFavoriteCurrencies() {
        viewModelScope.launch {
            isLoading.postValue(Event(true))
            when (val result = interactor.getFavoriteCurrencies()) {
                is Result.Success -> favoriteCurrencies.postValue(Event(result.value.mapToVoCurrenciesList()))
                is Result.Error   -> error.postValue(Event(result.error))
            }
            isLoading.postValue(Event(false))
        }
    }

    fun calculate(currentCurrency: VoCurrency) {

        val context = app.applicationContext
        val valueDouble = currentCurrency.value.fixForDoubleUsage(context).toDoubleOrNull() ?: 0.0
        val byn = valueDouble * currentCurrency.rate / currentCurrency.scale
        val list = favoriteCurrencies.value?.peekContent()?.toMutableList() ?: mutableListOf()

        for (item in list)
            if (currentCurrency.id != item.id) item.setValueWithNotify(byn / item.rate)
    }
}