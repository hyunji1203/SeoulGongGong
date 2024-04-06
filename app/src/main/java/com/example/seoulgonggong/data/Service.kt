package com.example.seoulgonggong.data

import retrofit2.http.GET

// 샘플 서비스 (이런 식으로 작성하면 된다는 예시)
interface Service {

    // url 은 이런 식으로 작성하면 될 것 같습니다 (예시는 공공서비스 데이터)
    // 리턴타입은...미정...
    @GET("/xml/ListPublicReservationSport/1/5/")
    fun getDataSample()
}