package com.example.seoulgonggong.data.service

import com.example.seoulgonggong.data.model.SportsFacilityResponse
import retrofit2.http.GET

interface PublicSportsFacilityService {

    @GET("/json/facilities/1/5/")
    fun getSportsFacility(): Result<SportsFacilityResponse>
}
