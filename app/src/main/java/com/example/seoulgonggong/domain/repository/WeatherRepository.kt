package com.example.seoulgonggong.domain.repository

import com.example.seoulgonggong.domain.model.BaseDateTime
import com.example.seoulgonggong.domain.model.Point
import com.example.seoulgonggong.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(
        baseDateTime: BaseDateTime,
        point: Point,
    ): Weather
}
