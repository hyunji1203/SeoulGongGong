package com.example.seoulgonggong.ui.uimodel.mapper

import com.example.seoulgonggong.domain.model.Weather
import com.example.seoulgonggong.ui.uimodel.UiWeather
import com.example.seoulgonggong.ui.uimodel.WeatherStatus

fun Weather.toUi(): UiWeather {
    return UiWeather(
        temperature = temperature.toInt(),
        weatherStatus = WeatherStatus.findByName(getStatus()),
    )
}
