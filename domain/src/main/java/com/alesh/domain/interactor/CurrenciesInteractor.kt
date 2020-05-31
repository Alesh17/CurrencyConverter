package com.alesh.domain.interactor

import com.alesh.domain.model.dto.Currency
import com.alesh.domain.model.result.Result
import com.alesh.domain.repository.CurrenciesRepository
import javax.inject.Inject

class CurrenciesInteractor @Inject constructor(
    private val repository: CurrenciesRepository
) {

    suspend fun getCurrencies(): Result<List<Currency>> {
        return repository.getCurrencies()
    }
}