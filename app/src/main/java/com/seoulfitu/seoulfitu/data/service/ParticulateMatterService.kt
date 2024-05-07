package com.seoulfitu.seoulfitu.data.service

import retrofit2.Response
import retrofit2.http.GET

interface ParticulateMatterService {
    @GET("json/RealtimeCityAir/1/25")
    suspend fun getParticulateMatter(): Response<com.seoulfitu.seoulfitu.data.model.ParticulateMatterResponse>
}
