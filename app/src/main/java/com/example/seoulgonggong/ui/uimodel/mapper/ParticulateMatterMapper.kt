package com.example.seoulgonggong.ui.uimodel.mapper

import com.example.seoulgonggong.domain.model.ParticulateMatter
import com.example.seoulgonggong.ui.uimodel.UiParticulateMatter

fun ParticulateMatter.toUi(): UiParticulateMatter {
    return UiParticulateMatter(
        observatory = msrsteNm,
        particulateMatter = pm10,
        particulateMatterStatus = idexNm,
    )
}
