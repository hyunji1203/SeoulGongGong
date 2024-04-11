package com.example.seoulgonggong.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("response")
    val response: WeatherData,
)

@Serializable
data class WeatherData(
    @SerialName("body")
    val body: WeatherBody,
)

@Serializable
data class WeatherBody(
    @SerialName("items")
    val items: Items,
)

@Serializable
data class Items(
    @SerialName("item")
    val item: List<ForecastResponse>,
)

@Serializable
data class ForecastResponse(
    @SerialName("baseDate")
    val baseDate: String,
    @SerialName("baseTime")
    val baseTime: String,
    @SerialName("category")
    val category: String,
    @SerialName("fcstDate")
    val forecastDate: String,
    @SerialName("fcstTime")
    val forecastTime: String,
    @SerialName("fcstValue")
    val forecastValue: String,
    @SerialName("nx")
    val nx: Int,
    @SerialName("ny")
    val ny: Int,
) {
    fun transformRainType(): String {
        return when (forecastValue.toInt()) {
            0 -> "없음"
            1, 4 -> "비"
            2, 3 -> "눈"
            else -> ""
        }
    }

    fun transformSky(): String {
        return when (forecastValue.toInt()) {
            1 -> "맑음"
            3 -> "구름많음"
            4 -> "흐림"
            else -> ""
        }
    }
}
