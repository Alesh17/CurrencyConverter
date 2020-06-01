package com.alesh.data.repository

import com.alesh.data.mapper.mapToCurrenciesList
import com.alesh.data.source.local.CurrenciesDataSource
import com.alesh.data.source.remote.CurrenciesApi
import com.alesh.data.util.safeApiCall
import com.alesh.domain.model.dto.Currency
import com.alesh.domain.model.result.Result
import com.alesh.domain.repository.CurrenciesRepository
import javax.inject.Inject

class CurrenciesRepositoryImpl @Inject constructor(
    private val api: CurrenciesApi,
    private val currencies: CurrenciesDataSource
) : CurrenciesRepository {

    override suspend fun getCurrencies(): Result<List<Currency>> {
        val currentCurrencies = currencies.getAll()
        return if (currentCurrencies.isEmpty()) {
            val result = safeApiCall { api.getCurrencies().mapToCurrenciesList() }
            if (result is Result.Success) currencies.setAll(result.value)
            result
        } else Result.Success(currentCurrencies)
    }

    override fun getFavoriteCurrencies() = currencies.getFavorites()

    override fun setFavoriteCurrency(id: Int) {
        val item = getCurrencyById(id)
        currencies.setFavorites(item)
    }

    override fun removeFromFavoriteCurrencies(id: Int) {
        val item = getCurrencyById(id)
        currencies.removeFromFavorites(item)
    }

    private fun getCurrencyById(id: Int): Currency {
        val predicate: (Currency) -> Boolean = { it.id == id }
        val items = currencies.getAll().filter(predicate)
        return items[0]
    }
}