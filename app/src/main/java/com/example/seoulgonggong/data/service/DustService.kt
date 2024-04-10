package com.example.seoulgonggong.data.service

import com.example.seoulgonggong.data.model.DustResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DustService {
    @GET("json/RealtimeCityAir/1/1")
    suspend fun getDust(
        @Query("MSRSTE_NM") msrsteNm: String,
    ): Response<DustResponse>
}
