package com.alesh.domain.model.dto

data class Currency(
    val id: Int,
    val abbreviation: String,
    val scale: Int,
    val name: String,
    val rate: Double
)