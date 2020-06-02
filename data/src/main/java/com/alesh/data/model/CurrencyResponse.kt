package com.alesh.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("Cur_ID") val id: Int?,
    @SerializedName("Cur_Abbreviation") val abbreviation: String?,
    @SerializedName("Cur_Scale") val scale: Int?,
    @SerializedName("Cur_OfficialRate") val rate: Double?
)