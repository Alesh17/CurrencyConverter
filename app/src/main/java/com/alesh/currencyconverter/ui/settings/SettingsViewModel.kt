package com.alesh.currencyconverter.ui.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alesh.domain.error.ApplicationErrors
import com.alesh.domain.interactor.CurrenciesInteractor
import com.alesh.domain.model.dto.Currency
import com.alesh.domain.model.result.Result
import com.alesh.currencyconverter.util.livedata.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val interactor: CurrenciesInteractor
) : ViewModel() {

    val currencies = MutableLiveData<Event<List<Currency>>>()
    val error = MutableLiveData<Event<ApplicationErrors>>()

    fun getCurrencies() {
        viewModelScope.launch {
            when (val result = interactor.getCurrencies()) {
                is Result.Success -> currencies.postValue(Event(result.value))
                is Result.Error   -> error.postValue(Event(result.error))
            }
        }
    }
}