package com.alesh.data.mapper

import com.alesh.data.model.CurrencyResponse
import com.alesh.domain.model.dto.Currency

fun List<CurrencyResponse>.mapToCurrenciesList(): List<Currency> {
    return this.map {
        Currency(
            id = it.id ?: throw IllegalArgumentException(),
            abbreviation = it.abbreviation ?: throw IllegalArgumentException(),
            scale = it.scale ?: throw IllegalArgumentException(),
            name = it.name ?: throw IllegalArgumentException(),
            rate = it.rate ?: throw IllegalArgumentException()
        )
    }
}