package com.alesh.domain.model.dto

data class Currency(
    var id: Int,
    var abbreviation: String,
    var scale: Int,
    var rate: Double
)