package com.seoulfitu.seoulfitu.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParticulateMatterResponse(
    @SerialName("RealtimeCityAir")
    val realTimeCityAir: RealtimeCityAir,
)

@Serializable
data class RealtimeCityAir(
    @SerialName("RESULT")
    val result: ResultWithCodeAndMsg,
    @SerialName("row")
    val particulateMatterRow: List<ParticulateMatterRow>,
)

@Serializable
data class ResultWithCodeAndMsg(
    @SerialName("CODE")
    val code: String,
    @SerialName("MESSAGE")
    val message: String,
)

@Serializable
data class ParticulateMatterRow(
    @SerialName("MSRSTE_NM")
    val msrsteNm: String,
    @SerialName("PM10")
    val pm10: Double,
    @SerialName("IDEX_NM")
    val idexNm: String,
)
