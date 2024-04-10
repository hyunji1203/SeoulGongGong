package com.example.seoulgonggong.data.model.mapper

import com.example.seoulgonggong.data.model.DustResponse
import com.example.seoulgonggong.domain.model.Dust

fun DustResponse.toDomain(): Dust {
    return Dust(
        msrsteNm = this.realTimeCityAir.row.msrsteNm,
        pm10 = this.realTimeCityAir.row.pm10,
    )
}
