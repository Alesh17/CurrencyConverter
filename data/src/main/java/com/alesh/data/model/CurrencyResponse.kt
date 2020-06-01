package com.alesh.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("Cur_ID") val id: Int?,                           // 170
    @SerializedName("Cur_Abbreviation") val abbreviation: String?,    // USD
    @SerializedName("Cur_Scale") val scale: Int?,                     // 1
    @SerializedName("Cur_OfficialRate") val rate: Double?             // 2.4
)