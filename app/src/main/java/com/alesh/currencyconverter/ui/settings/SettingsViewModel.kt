package com.alesh.currencyconverter.ui.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alesh.currencyconverter.ui.currencies.mapper.mapToVoCurrenciesList
import com.alesh.currencyconverter.ui.model.VoCurrency
import com.alesh.currencyconverter.util.livedata.Event
import com.alesh.domain.error.ApplicationErrors
import com.alesh.domain.interactor.CurrenciesInteractor
import com.alesh.domain.model.result.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val interactor: CurrenciesInteractor
) : ViewModel() {

    val currencies = MutableLiveData<Event<List<VoCurrency>>>()
    val error = MutableLiveData<Event<ApplicationErrors>>()
    val isLoading = MutableLiveData<Event<Boolean>>()

    fun getCurrencies() {
        viewModelScope.launch {
            isLoading.postValue(Event(true))
            when (val result = interactor.getAllCurrencies()) {
                is Result.Success -> currencies.postValue(Event(result.value.mapToVoCurrenciesList()))
                is Result.Error   -> error.postValue(Event(result.error))
            }
            isLoading.postValue(Event(false))
        }
    }

    fun addToFavorites(currency: VoCurrency) {
        interactor.addFavoriteCurrency(currency.id)
    }

    fun removeFromFavorites(currency: VoCurrency) {
        interactor.removeFromFavoriteCurrencies(currency.id)
    }
}