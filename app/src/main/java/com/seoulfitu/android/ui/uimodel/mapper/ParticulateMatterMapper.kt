package com.seoulfitu.android.ui.uimodel.mapper

import com.seoulfitu.android.domain.model.ParticulateMatter
import com.seoulfitu.android.ui.uimodel.UiParticulateMatter

fun ParticulateMatter.toUi(): UiParticulateMatter {
    return UiParticulateMatter(
        observatory = msrsteNm,
        particulateMatter = pm10,
        particulateMatterStatus = idexNm,
    )
}
