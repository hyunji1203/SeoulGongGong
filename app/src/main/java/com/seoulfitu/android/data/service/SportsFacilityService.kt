package com.seoulfitu.android.data.service

import com.seoulfitu.android.data.model.response.SportsFacilityResponse
import retrofit2.Response
import retrofit2.http.GET

interface SportsFacilityService {

    @GET("json/facilities/1/1000")
    suspend fun getSportsFacility(): Response<SportsFacilityResponse>
}
