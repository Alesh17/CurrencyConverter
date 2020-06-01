package com.alesh.data.source.local

import com.alesh.domain.model.dto.Currency
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrenciesDataSource @Inject constructor() {

    private var allCurrencies: MutableList<Currency> = mutableListOf()
    private val favoriteCurrencies: MutableList<Currency> = mutableListOf()

    fun getAll() = allCurrencies as List<Currency>

    fun setAll(currencies: List<Currency>) = allCurrencies.addAll(currencies)

    fun getFavorites() = favoriteCurrencies as List<Currency>

    fun setFavorites(currency: Currency) = favoriteCurrencies.add(currency)

    fun removeFromFavorites(currency: Currency) = favoriteCurrencies.remove(currency)
}