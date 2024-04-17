package com.seoulfitu.android.domain.model

data class Weather(
    val forecastDate: String,
    val forecastTime: String,
    val temperature: Double,
    val sky: String,
    val precipitationType: String,
) {
    fun getStatus(): String {
        return if (precipitationType == "" || precipitationType == "없음") {
            sky
        } else {
            precipitationType
        }
    }
}
