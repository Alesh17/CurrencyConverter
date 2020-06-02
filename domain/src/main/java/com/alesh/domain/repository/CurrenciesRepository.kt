package com.alesh.domain.repository

import com.alesh.domain.model.dto.Currency
import com.alesh.domain.model.result.Result

interface CurrenciesRepository {

    suspend fun getCurrencies(): Result<List<Currency>>

    fun setCurrencies(currencies: List<Currency>)

    suspend fun getFavoriteCurrencies(): Result<List<Currency>>

    fun addFavoriteCurrency(id: Int)

    fun removeFromFavoriteCurrencies(id: Int)
}