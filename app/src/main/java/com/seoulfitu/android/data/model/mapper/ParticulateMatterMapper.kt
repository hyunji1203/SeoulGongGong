package com.seoulfitu.android.data.model.mapper

import com.seoulfitu.android.domain.model.ParticulateMatter

fun com.seoulfitu.android.data.model.ParticulateMatterRow.toDomain(): ParticulateMatter {
    return ParticulateMatter(
        msrsteNm = msrsteNm,
        pm10 = pm10.toInt(),
        idexNm = idexNm,
    )
}
