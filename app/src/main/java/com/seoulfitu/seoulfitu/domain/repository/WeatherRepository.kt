package com.seoulfitu.seoulfitu.domain.repository

import com.seoulfitu.seoulfitu.domain.model.BaseDateTime
import com.seoulfitu.seoulfitu.domain.model.Point
import com.seoulfitu.seoulfitu.domain.model.Weather


interface WeatherRepository {
    suspend fun getWeather(
        baseDateTime: BaseDateTime,
        point: Point,
    ): Weather
}
