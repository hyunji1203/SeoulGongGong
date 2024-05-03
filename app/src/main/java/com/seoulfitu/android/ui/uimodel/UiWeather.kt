package com.seoulfitu.android.ui.uimodel

data class UiWeather(
    val temperature: Int = -100,
    val weatherStatus: WeatherStatus = WeatherStatus.SUN,
)
