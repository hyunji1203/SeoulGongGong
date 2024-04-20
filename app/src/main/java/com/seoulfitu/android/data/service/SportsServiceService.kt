package com.seoulfitu.android.data.service

import com.seoulfitu.android.data.model.response.SportsServiceResponse
import retrofit2.Response
import retrofit2.http.GET

interface SportsServiceService {
    @GET("json/ListPublicReservationSport/1/512")
    suspend fun getServices(): Response<SportsServiceResponse>
}