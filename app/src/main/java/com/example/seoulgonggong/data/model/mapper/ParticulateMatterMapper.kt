package com.example.seoulgonggong.data.model.mapper

import com.example.seoulgonggong.data.model.ParticulateMatterRow
import com.example.seoulgonggong.domain.model.ParticulateMatter

fun ParticulateMatterRow.toDomain(): ParticulateMatter {
    return ParticulateMatter(
        msrsteNm = msrsteNm,
        pm10 = pm10.toInt(),
        idexNm = idexNm,
    )
}
