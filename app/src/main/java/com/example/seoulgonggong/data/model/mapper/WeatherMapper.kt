package com.example.seoulgonggong.data.model.mapper

import com.example.seoulgonggong.data.model.ForecastResponse
import com.example.seoulgonggong.domain.model.Forecast
import com.example.seoulgonggong.domain.model.WeatherCategory

fun ForecastResponse.toDomain(): Forecast {
    return Forecast(
        baseDate = baseDate,
        baseTime = baseTime,
        category = WeatherCategory.findByName(category),
        forecastDate = forecastDate,
        forecastTime = forecastTime,
        forecastValue = forecastValue,
        nx = nx,
        ny = ny,
    )
}
