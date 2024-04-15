package com.example.seoulgonggong.data.service

import com.example.seoulgonggong.data.model.response.ReverseGeocodeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ReverseGeocodingService {
    @GET("./")
    suspend fun reverseGeocode(
        @Query("coords") coords: String,
        @Query("output") output: String = "json"
    ): Response<ReverseGeocodeResponse>
}