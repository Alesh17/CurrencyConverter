package com.alesh.data.source.local

import com.alesh.domain.model.dto.Currency
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrenciesDataSource @Inject constructor() {

    private val currencies: MutableList<Currency> = mutableListOf()

    fun getAll() = currencies as List<Currency>

    fun setAll(currencies: List<Currency>) {
        this.currencies.clear()
        this.currencies.addAll(currencies)
    }

    fun getFavorites(): List<Currency> {
        val predicate: (Currency) -> Boolean = { it.isFavorite }
        return currencies.filter(predicate)
    }

    fun addFavorite(id: Int) {
        val item = getCurrencyById(id)
        changeFavoriteState(item, true)
    }

    fun removeFavorite(id: Int) {
        val item = getCurrencyById(id)
        changeFavoriteState(item, false)
    }

    private fun changeFavoriteState(currency: Currency, state: Boolean) {
        for (item in currencies)
            if (item.id == currency.id)
                item.isFavorite = state
    }

    private fun getCurrencyById(id: Int): Currency {
        val predicate: (Currency) -> Boolean = { it.id == id }
        val items = currencies.filter(predicate)
        return items[0]
    }
}