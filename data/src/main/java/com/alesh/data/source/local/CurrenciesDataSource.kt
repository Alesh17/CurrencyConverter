package com.alesh.data.source.local

import android.util.Log
import com.alesh.domain.model.dto.Currency
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrenciesDataSource @Inject constructor() {

    private val allCurrencies: MutableList<Currency> = mutableListOf()

    fun getAll() = allCurrencies as List<Currency>

    fun setAll(currencies: List<Currency>) {
        allCurrencies.clear()
        allCurrencies.addAll(currencies)
    }

    fun getFavorites(): List<Currency> {
        val predicate: (Currency) -> Boolean = { it.isFavorite }
        return allCurrencies.filter(predicate)
    }

    fun addFavorite(id: Int) {
        val item = getCurrencyById(id)
        changeFavoriteState(item, true)
        Log.i("alesh! ${item.abbreviation}", "true")
    }

    fun removeFavorite(id: Int) {
        val item = getCurrencyById(id)
        changeFavoriteState(item, false)
        Log.i("alesh! ${item.abbreviation}", "false")
    }

    private fun changeFavoriteState(currency: Currency, state: Boolean) {
        for (item in allCurrencies)
            if (item.id == currency.id)
                item.isFavorite = state
    }

    private fun getCurrencyById(id: Int): Currency {
        val predicate: (Currency) -> Boolean = { it.id == id }
        val items = allCurrencies.filter(predicate)
        return items[0]
    }
}