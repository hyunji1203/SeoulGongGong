package com.example.seoulgonggong.data.service

import com.example.seoulgonggong.data.model.PublicServiceResponse
import retrofit2.Response
import retrofit2.http.GET

interface PublicServiceService {
    @GET("/xml/ListPublicReservationSport/1/5/")
    suspend fun getData(): Response<PublicServiceResponse>
}