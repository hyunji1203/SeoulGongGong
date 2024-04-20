package com.seoulfitu.android.domain.repository

import com.seoulfitu.android.domain.model.BaseDateTime
import com.seoulfitu.android.domain.model.Point
import com.seoulfitu.android.domain.model.Weather


interface WeatherRepository {
    suspend fun getWeather(
        baseDateTime: BaseDateTime,
        point: Point,
    ): Weather
}
