package com.example.seoulgonggong.data.service

import com.example.seoulgonggong.data.model.ParticulateMatterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ParticulateMatterService {
    @GET("json/RealtimeCityAir/1/1")
    suspend fun getParticulateMatter(
        @Query("MSRSTE_NM") msrsteNm: String,
    ): Response<ParticulateMatterResponse>
}
