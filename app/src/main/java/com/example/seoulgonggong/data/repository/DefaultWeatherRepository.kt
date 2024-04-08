package com.example.seoulgonggong.data.repository

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.seoulgonggong.data.model.ForecastResponse
import com.example.seoulgonggong.data.service.WeatherService
import com.example.seoulgonggong.domain.model.Weather
import com.example.seoulgonggong.domain.model.WeatherCategory.PTY
import com.example.seoulgonggong.domain.model.WeatherCategory.SKY
import com.example.seoulgonggong.domain.model.WeatherCategory.TMP
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
    ): Weather {
        val response = weatherService.getForecast(serviceKey, baseDate, baseTime, nx, ny)
        if (response.isSuccessful) {
            Log.d("test", "성공 문")
            val forecastDateTimeMap = mutableMapOf<String, Weather>()
            val forecasts = response.body()?.response?.body?.items?.item.orEmpty()

            for (forecast in forecasts) {
                if (forecastDateTimeMap["${forecast.forecastDate}/${forecast.forecastTime}"] == null) {
                    forecastDateTimeMap["${forecast.forecastDate}/${forecast.forecastTime}"] =
                        Weather(
                            forecastDate = forecast.forecastDate,
                            forecastTime = forecast.forecastTime,
                        )
                }

                forecastDateTimeMap["${forecast.forecastDate}/${forecast.forecastTime}"]?.apply {
                    when (forecast.category) {
                        PTY.toString() -> precipitationType = transformRainType(forecast)
                        SKY.toString() -> sky = transformSky(forecast)
                        TMP.toString() -> temperature = forecast.forecastValue.toDouble()
                        else -> {}
                    }
                }
            }

            val list = forecastDateTimeMap.values.toMutableList()
            list.sortWith { f1, f2 ->
                val f1DateTime = "${f1.forecastDate}${f1.forecastTime}"
                val f2DateTime = "${f2.forecastDate}${f2.forecastTime}"

                return@sortWith f1DateTime.compareTo(f2DateTime)
            }
            return list.first()
        } else {
            Log.d("test", "else 문")
            throw NetworkErrorException("네트워크 오류")
        }
    }

    private fun transformRainType(forecast: ForecastResponse): String {
        return when (forecast.forecastValue.toInt()) {
            0 -> "없음"
            1, 4 -> "비"
            2, 3 -> "눈"
            else -> ""
        }
    }

    private fun transformSky(forecast: ForecastResponse): String {
        return when (forecast.forecastValue.toInt()) {
            1 -> "맑음"
            3 -> "구름많음"
            4 -> "흐림"
            else -> ""
        }
    }
}
