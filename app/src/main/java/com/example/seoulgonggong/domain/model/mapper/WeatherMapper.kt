package com.example.seoulgonggong.domain.model.mapper

import com.example.seoulgonggong.data.model.ForecastResponse
import com.example.seoulgonggong.domain.model.Forecast

fun ForecastResponse.toDomain(): Forecast {
    return Forecast(
        baseDate = baseDate,
        baseTime = baseTime,
        category = category,
        forecastDate = forecastDate,
        forecastTime = forecastTime,
        forecastValue = forecastValue,
        nx = nx,
        ny = ny,
    )
}
