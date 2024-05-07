package com.seoulfitu.seoulfitu.ui.uimodel.mapper

import com.seoulfitu.seoulfitu.domain.model.Weather
import com.seoulfitu.seoulfitu.ui.uimodel.UiWeather
import com.seoulfitu.seoulfitu.ui.uimodel.WeatherStatus

fun Weather.toUi(): UiWeather {
    return UiWeather(
        temperature = temperature.toInt(),
        weatherStatus = WeatherStatus.findByName(getStatus()),
    )
}
