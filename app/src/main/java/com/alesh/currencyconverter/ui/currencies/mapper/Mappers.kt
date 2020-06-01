package com.alesh.currencyconverter.ui.currencies.mapper

import com.alesh.currencyconverter.ui.currencies.adapter.model.VoCurrency
import com.alesh.domain.model.dto.Currency

fun List<Currency>.mapToVoCurrenciesList(): List<VoCurrency> {
    return this.map {
        VoCurrency().apply {
            id = it.id
            abbreviation = it.abbreviation
            scale = it.scale
            abbreviation = it.abbreviation
            rate = it.rate
        }
    }
}