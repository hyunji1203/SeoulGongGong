package com.example.seoulgonggong.ui.uimodel.mapper

import com.example.seoulgonggong.domain.model.Weather
import com.example.seoulgonggong.domain.model.WeatherStatus
import com.example.seoulgonggong.ui.uimodel.UiWeather

fun Weather.toUi(): UiWeather {
    return UiWeather(
        temperature = temperature.toInt(),
        weatherStatus = WeatherStatus.findByName(getStatus()),
    )
}
