package com.seoulfitu.seoulfitu.ui.uimodel

data class UiWeather(
    val temperature: Int = -100,
    val weatherStatus: WeatherStatus = WeatherStatus.SUN,
){
    companion object {
        const val INIT_VALUE = -100
    }
}
