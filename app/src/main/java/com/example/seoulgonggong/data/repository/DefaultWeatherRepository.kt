package com.example.seoulgonggong.data.repository

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.seoulgonggong.data.model.mapper.toDomain
import com.example.seoulgonggong.data.service.WeatherService
import com.example.seoulgonggong.domain.model.Forecast
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
            Log.d("test", "성공 문")
            return response.body()?.response?.body?.items?.item?.map { it.toDomain() }
                ?: throw NetworkErrorException("network error")
        } else {
            Log.d("test", "else 문")
            throw NetworkErrorException("네트워크 오류")
        }
    }
}
