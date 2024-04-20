package com.seoulfitu.android.ui.uimodel.mapper

import com.seoulfitu.android.domain.model.Weather
import com.seoulfitu.android.ui.uimodel.UiWeather
import com.seoulfitu.android.ui.uimodel.WeatherStatus

fun Weather.toUi(): UiWeather {
    return UiWeather(
        temperature = temperature.toInt(),
        weatherStatus = WeatherStatus.findByName(getStatus()),
    )
}
