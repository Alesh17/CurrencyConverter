package com.alesh.data.repository

import com.alesh.data.mapper.mapToCurrenciesList
import com.alesh.data.source.local.CurrenciesDataSource
import com.alesh.data.source.remote.CurrenciesApi
import com.alesh.data.util.safeApiCall
import com.alesh.domain.model.dto.Currency
import com.alesh.domain.model.result.Result
import com.alesh.domain.repository.CurrenciesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrenciesRepositoryImpl @Inject constructor(
    private val api: CurrenciesApi,
    private val currenciesDataSource: CurrenciesDataSource
) : CurrenciesRepository {

    private var isFirstLaunch = true

    override suspend fun getAllCurrencies(): Result<List<Currency>> {
        val currentCurrencies = currenciesDataSource.getAll()
        return if (currentCurrencies.isEmpty()) {
            val result = safeApiCall { api.getCurrencies().mapToCurrenciesList() }
            if (result is Result.Success) {
                val initialList = doOnFirstLaunch(result.value as MutableList)
                Result.Success(initialList)
            } else result
        } else Result.Success(currentCurrencies)
    }

    override suspend fun getFavoriteCurrencies(): Result<List<Currency>> {
        return if (isFirstLaunch) {
            val result = getAllCurrencies()
            if (result is Result.Error) result
            else Result.Success(currenciesDataSource.getFavorites())
        } else Result.Success(currenciesDataSource.getFavorites())
    }

    override fun addFavoriteCurrency(id: Int) {
        currenciesDataSource.addFavorite(id)
    }

    override fun removeFromFavoriteCurrencies(id: Int) {
        currenciesDataSource.removeFavorite(id)
    }

    private fun doOnFirstLaunch(currencies: MutableList<Currency>): MutableList<Currency> {
        if (isFirstLaunch) {
            currencies.add(Currency(0, "BYR", 1, 1.0, true))
            for (item in currencies)
                if (item.abbreviation == "USD" || item.abbreviation == "EUR")
                    item.isFavorite = true
            currenciesDataSource.setAll(currencies)
            isFirstLaunch = false
        }
        return currencies
    }
}