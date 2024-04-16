package com.example.seoulgonggong.data.service

import com.example.seoulgonggong.data.model.SportsServiceResponse
import retrofit2.Response
import retrofit2.http.GET

interface SportsServiceService {
    @GET("json/ListPublicReservationSport/1/512")
    suspend fun getServices(): Response<SportsServiceResponse>
}