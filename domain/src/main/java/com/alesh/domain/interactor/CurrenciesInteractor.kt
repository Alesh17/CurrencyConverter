package com.alesh.domain.interactor

import com.alesh.domain.repository.CurrenciesRepository
import javax.inject.Inject

class CurrenciesInteractor @Inject constructor(
    private val repository: CurrenciesRepository
) {

    suspend fun getAllCurrencies() = repository.getAllCurrencies()

    suspend fun getFavoriteCurrencies() = repository.getFavoriteCurrencies()

    fun addFavoriteCurrency(id: Int) = repository.addFavoriteCurrency(id)

    fun removeFromFavoriteCurrencies(id: Int) = repository.removeFromFavoriteCurrencies(id)
}