package com.example.seoulgonggong.data.repository

import android.accounts.NetworkErrorException
import com.example.seoulgonggong.data.service.WeatherService
import com.example.seoulgonggong.domain.model.Forecast
import com.example.seoulgonggong.domain.model.mapper.toData
import com.example.seoulgonggong.domain.repository.WeatherRepository

class DefaultWeatherRepository(
    private val weatherService: WeatherService,
) : WeatherRepository {
    override suspend fun getWeather(
        serviceKey: String,
        baseDate: String,
        baseTime: String,
        nx: Int,
        ny: Int,
    ): List<Forecast> {
        val response = weatherService.getForecast(serviceKey, baseDate, baseTime, nx, ny)
        if (response.isSuccessful) {
            return response.body()?.map { it.toData() } ?: throw NetworkErrorException("network error")
        } else {
            throw NetworkErrorException("네트워크 오류")
        }
    }
}
