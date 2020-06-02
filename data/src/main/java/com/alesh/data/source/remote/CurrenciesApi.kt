package com.alesh.data.source.remote

import com.alesh.data.model.CurrencyResponse
import retrofit2.http.GET

interface CurrenciesApi {

    @GET("api/exrates/rates?periodicity=0")
    suspend fun getCurrencies(): List<CurrencyResponse>
}