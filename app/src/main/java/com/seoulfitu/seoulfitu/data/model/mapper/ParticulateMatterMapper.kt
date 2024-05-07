package com.seoulfitu.seoulfitu.data.model.mapper

import com.seoulfitu.seoulfitu.domain.model.ParticulateMatter

fun com.seoulfitu.seoulfitu.data.model.ParticulateMatterRow.toDomain(): ParticulateMatter {
    return ParticulateMatter(
        msrsteNm = msrsteNm,
        pm10 = pm10.toInt(),
        idexNm = idexNm,
    )
}
