package com.alesh.domain.interactor

import com.alesh.domain.model.dto.Currency
import com.alesh.domain.repository.CurrenciesRepository
import javax.inject.Inject

class CurrenciesInteractor @Inject constructor(
    private val repository: CurrenciesRepository
) {

    suspend fun getCurrencies() = repository.getCurrencies()

    fun setCurrencies(currencies: List<Currency>) = repository.setCurrencies(currencies)

    suspend fun getFavoriteCurrencies() = repository.getFavoriteCurrencies()

    fun addFavoriteCurrency(id: Int) = repository.addFavoriteCurrency(id)

    fun removeFromFavoriteCurrencies(id: Int) = repository.removeFromFavoriteCurrencies(id)
}