package com.example.seoulgonggong.data.service

import com.example.seoulgonggong.data.model.response.GeocodeResponse
import com.example.seoulgonggong.data.model.response.ReverseGeocodeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocoderService {
    @GET("map-geocode/v2/geocode")
    suspend fun getGeocode(@Query("query") query:String):Response<GeocodeResponse>

    @GET("map-reversegeocode/v2/gc")
    suspend fun getReverseGeocode(
        @Query("coords") coords: String,
        @Query("output") output: String = "json"
    ): Response<ReverseGeocodeResponse>
}