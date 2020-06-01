package com.alesh.domain.interactor

import com.alesh.domain.repository.CurrenciesRepository
import javax.inject.Inject

class CurrenciesInteractor @Inject constructor(
    private val repository: CurrenciesRepository
) {

    suspend fun getCurrencies() = repository.getCurrencies()

    fun getFavoriteCurrencies() = repository.getFavoriteCurrencies()

    fun setFavoriteCurrency(id: Int) = repository.setFavoriteCurrency(id)

    fun removeFromFavoriteCurrencies(id: Int) = repository.removeFromFavoriteCurrencies(id)
}