package com.alesh.data.repository

import com.alesh.data.mapper.mapToCurrenciesList
import com.alesh.data.source.remote.CurrenciesApi
import com.alesh.data.util.safeApiCall
import com.alesh.domain.model.dto.Currency
import com.alesh.domain.model.result.Result
import com.alesh.domain.repository.CurrenciesRepository
import javax.inject.Inject

class CurrenciesRepositoryImpl @Inject constructor(
    private val api: CurrenciesApi
) : CurrenciesRepository {

    override suspend fun getCurrencies(): Result<List<Currency>> {
        return safeApiCall { api.getCurrencies().mapToCurrenciesList() }
    }
}