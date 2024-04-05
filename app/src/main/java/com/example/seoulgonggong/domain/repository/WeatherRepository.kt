package com.example.seoulgonggong.domain.repository

import com.example.seoulgonggong.domain.model.Forecast

interface WeatherRepository {
    suspend fun getWeather(
        serviceKey: String,
        baseDate: String,
        baseTime: String,
        nx: Int,
        ny: Int,
    ): List<Forecast>
}
