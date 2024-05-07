package com.seoulfitu.seoulfitu.ui.uimodel.mapper

import com.seoulfitu.seoulfitu.domain.model.ParticulateMatter
import com.seoulfitu.seoulfitu.ui.uimodel.UiParticulateMatter

fun ParticulateMatter.toUi(): UiParticulateMatter {
    return UiParticulateMatter(
        observatory = msrsteNm,
        particulateMatter = pm10,
        particulateMatterStatus = idexNm,
    )
}
