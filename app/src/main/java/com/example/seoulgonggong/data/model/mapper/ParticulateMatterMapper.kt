package com.example.seoulgonggong.data.model.mapper

import com.example.seoulgonggong.data.model.ParticulateMatterResponse
import com.example.seoulgonggong.domain.model.ParticulateMatter

fun ParticulateMatterResponse.toDomain(): ParticulateMatter {
    return ParticulateMatter(
        msrsteNm = realTimeCityAir.row[0].msrsteNm,
        pm10 = realTimeCityAir.row[0].pm10.toInt(),
        idexNm = realTimeCityAir.row[0].idexNm,
    )
}
