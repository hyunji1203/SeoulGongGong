package com.example.seoulgonggong.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DustResponse(
    @SerialName("RealtimeCityAir")
    val realTimeCityAir: RealtimeCityAir,
)

@Serializable
data class RealtimeCityAir(
    @SerialName("row")
    val row: Row,
)

@Serializable
data class Row(
    @SerialName("MSRSTE_NM")
    val msrsteNm: String,
    @SerialName("PM10")
    val pm10: Int,
)
