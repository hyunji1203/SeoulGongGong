package com.seoulfitu.seoulfitu.data.service

import com.seoulfitu.seoulfitu.data.model.response.SportsServiceResponse
import retrofit2.Response
import retrofit2.http.GET

interface SportsServiceService {
    @GET("json/ListPublicReservationSport/1/512")
    suspend fun getServices(): Response<SportsServiceResponse>
}
