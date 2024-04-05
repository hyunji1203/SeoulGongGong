package com.example.seoulgonggong.domain.model

data class Forecast(
    val baseDate: String,
    val baseTime: String,
    val category: String,
    val forecastDate: String,
    val forecastTime: String,
    val forecastValue: String,
    val nx: Int,
    val ny: Int,
)
