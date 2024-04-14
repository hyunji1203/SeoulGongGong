package com.example.seoulgonggong.data.repository

import android.accounts.NetworkErrorException
import com.example.seoulgonggong.BuildConfig.OPEN_DATA_SERVICE_KEY
import com.example.seoulgonggong.data.model.ForecastResponse
import com.example.seoulgonggong.data.service.WeatherService
import com.example.seoulgonggong.domain.model.BaseDateTime
import com.example.seoulgonggong.domain.model.Point
import com.example.seoulgonggong.domain.model.Weather
import com.example.seoulgonggong.domain.model.WeatherCategory.PTY
import com.example.seoulgonggong.domain.model.WeatherCategory.SKY
import com.example.seoulgonggong.domain.model.WeatherCategory.TMP
import com.example.seoulgonggong.domain.repository.WeatherRepository
import javax.inject.Inject

class DefaultWeatherRepository
    @Inject
    constructor(
        private val weatherService: WeatherService,
    ) : WeatherRepository {
        override suspend fun getWeather(
            baseDateTime: BaseDateTime,
            point: Point,
        ): Weather {
            val response =
                weatherService.getForecast(
                    serviceKey = OPEN_DATA_SERVICE_KEY,
                    baseDate = baseDateTime.baseDate,
                    baseTime = baseDateTime.baseTime,
                    nx = point.nx,
                    ny = point.ny,
                )

            if (response.isSuccessful) {
                val forecasts = response.body()?.response?.body?.items?.item.orEmpty()
                val forecastDateTimeMap: MutableMap<String, Weather> = sortForecastByTime(forecasts)

                val list = forecastDateTimeMap.values.toMutableList()
                list.sortWith { f1, f2 ->
                    val f1DateTime = "${f1.forecastDate}${f1.forecastTime}"
                    val f2DateTime = "${f2.forecastDate}${f2.forecastTime}"

                    return@sortWith f1DateTime.compareTo(f2DateTime)
                }
                return list.first()
            } else {
                throw NetworkErrorException()
            }
        }

        private fun sortForecastByTime(forecasts: List<ForecastResponse>): MutableMap<String, Weather> {
            val forecastDateTimeMap = mutableMapOf<String, Weather>()
            for (forecast in forecasts) {
                if (forecastDateTimeMap["${forecast.forecastDate}/${forecast.forecastTime}"] == null) {
                    forecastDateTimeMap["${forecast.forecastDate}/${forecast.forecastTime}"] =
                        Weather(forecast.forecastDate, forecast.forecastTime)
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
            return forecastDateTimeMap
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
