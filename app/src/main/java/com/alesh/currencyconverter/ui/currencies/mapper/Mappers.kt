package com.alesh.currencyconverter.ui.currencies.mapper

import com.alesh.currencyconverter.ui.model.VoCurrency
import com.alesh.domain.model.dto.Currency

fun List<Currency>.mapToVoCurrenciesList(): List<VoCurrency> {
    return this.map {
        VoCurrency().apply {
            id = it.id
            scale = it.scale
            abbreviation = it.abbreviation
            rate = it.rate
            isFavorite = it.isFavorite
        }
    }
}