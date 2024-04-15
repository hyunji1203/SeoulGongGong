package com.example.seoulgonggong.data.service

import com.example.seoulgonggong.data.model.response.GeocodeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {
    @GET("./")
    suspend fun geocode(@Query("query") query:String):Response<GeocodeResponse>
}