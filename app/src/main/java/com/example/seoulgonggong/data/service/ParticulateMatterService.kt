package com.example.seoulgonggong.data.service

import com.example.seoulgonggong.data.model.ParticulateMatterResponse
import retrofit2.Response
import retrofit2.http.GET

interface ParticulateMatterService {
    @GET("json/RealtimeCityAir/1/25")
    suspend fun getParticulateMatter(): Response<ParticulateMatterResponse>
}
