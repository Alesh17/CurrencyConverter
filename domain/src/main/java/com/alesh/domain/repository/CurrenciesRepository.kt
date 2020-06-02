package com.alesh.domain.repository

import com.alesh.domain.model.dto.Currency
import com.alesh.domain.model.result.Result

interface CurrenciesRepository {

    suspend fun getAllCurrencies(): Result<List<Currency>>

    suspend fun getFavoriteCurrencies(): Result<List<Currency>>

    fun addFavoriteCurrency(id: Int)

    fun removeFromFavoriteCurrencies(id: Int)
}