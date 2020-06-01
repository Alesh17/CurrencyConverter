package com.alesh.domain.repository

import com.alesh.domain.model.dto.Currency
import com.alesh.domain.model.result.Result

interface CurrenciesRepository {

    suspend fun getCurrencies(): Result<List<Currency>>

    fun getFavoriteCurrencies(): List<Currency>

    fun setFavoriteCurrency(id: Int)

    fun removeFromFavoriteCurrencies(id: Int)
}