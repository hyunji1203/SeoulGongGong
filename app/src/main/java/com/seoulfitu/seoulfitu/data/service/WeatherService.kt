package com.seoulfitu.seoulfitu.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/1360000/VilageFcstInfoService_2.0/getVilageFcst?pageNo=1&numOfRows500&dataType=JSON")
    suspend fun getForecast(
        @Query("serviceKey") serviceKey: String,
        @Query("base_date") baseDate: String,
        @Query("base_time") baseTime: String,
        @Query("nx") nx: Int,
        @Query("ny") ny: Int,
    ): Response<com.seoulfitu.seoulfitu.data.model.WeatherResponse>
}
