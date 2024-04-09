package com.example.seoulgonggong.data

import com.example.seoulgonggong.data.model.DataPublicService
import retrofit2.Response
import retrofit2.http.GET

// 샘플 서비스 (이런 식으로 작성하면 된다는 예시)
interface PublicServiceService {
    @GET("/xml/ListPublicReservationSport/1/5/")
    suspend fun getDataSample(): Response<DataPublicService>
}