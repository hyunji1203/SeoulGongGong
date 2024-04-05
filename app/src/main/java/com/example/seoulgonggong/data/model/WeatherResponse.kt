package com.example.seoulgonggong.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class WeatherResponse(
    @SerializedName("response")
    val response: WeatherData,
)

data class WeatherData(
    @SerializedName("header")
    val header: WeatherHeader,
    @SerializedName("body")
    val body: WeatherBody,
)

data class WeatherHeader(
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("resultMsg")
    val resultMsg: String,
)

data class WeatherBody(
    @SerializedName("items")
    val items: Items,
)

data class Items(
    @SerializedName("item")
    val item: List<ForecastResponse>,
)

@Serializable
data class ForecastResponse(
    @SerializedName("baseDate")
    val baseDate: String,
    @SerializedName("baseTime")
    val baseTime: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("fcstDate")
    val forecastDate: String,
    @SerializedName("fcstTime")
    val forecastTime: String,
    @SerializedName("fcstValue")
    val forecastValue: String,
    @SerializedName("nx")
    val nx: Int,
    @SerializedName("ny")
    val ny: Int,
)
