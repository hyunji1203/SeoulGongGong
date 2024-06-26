package com.seoulfitu.seoulfitu.ui.uimodel

enum class WeatherStatus(val currentStatus: String) {
    SUN("맑음"),
    LITTLE_SUNNY("구름많음"),
    CLOUD("흐림"),
    RAIN("비"),
    SNOW("눈"), ;

    companion object {
        fun findByName(text: String): WeatherStatus {
            return WeatherStatus.values().find { it.currentStatus == text } ?: SUN
        }
    }
}
