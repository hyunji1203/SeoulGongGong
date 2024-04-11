package com.example.seoulgonggong.data.model.mapper

import com.example.seoulgonggong.data.model.Row
import com.example.seoulgonggong.domain.model.ParticulateMatter

fun Row.toDomain(): ParticulateMatter {
    return ParticulateMatter(
        msrsteNm = msrsteNm,
        pm10 = pm10.toInt(),
        idexNm = idexNm,
    )
}
