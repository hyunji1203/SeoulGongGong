package com.example.seoulgonggong.domain.model

data class Weather(
    val forecastDate: String,
    val forecastTime: String,
    var temperature: Double = 0.0,
    var sky: String = "",
    var precipitation: Int = 0,
    var precipitationType: String = "",
) {
    fun getStatus(): String {
        return if (precipitationType == "" || precipitationType == "없음") {
            sky
        } else {
            precipitationType
        }
    }
}
