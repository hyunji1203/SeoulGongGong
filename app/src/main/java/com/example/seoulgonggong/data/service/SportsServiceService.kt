package com.example.seoulgonggong.data.service

import com.example.seoulgonggong.data.model.SportsServiceResponse
import retrofit2.Response
import retrofit2.http.GET

interface SportsServiceService {
    @GET("json/ListPublicReservationSport/1/5")
    suspend fun getData(): Response<SportsServiceResponse>
}