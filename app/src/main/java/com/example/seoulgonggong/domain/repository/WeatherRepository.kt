package com.example.seoulgonggong.domain.repository

import com.example.seoulgonggong.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(
        serviceKey: String,
        baseDate: String,
        baseTime: String,
        nx: Int,
        ny: Int,
    ): Weather
}
