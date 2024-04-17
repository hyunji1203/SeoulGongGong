package com.seoulfitu.android.domain.repository

import com.seoulfitu.android.domain.model.ParticulateMatter

interface ParticulateMatterRepository {
    suspend fun getParticulateMatter(msrsteNm: String): ParticulateMatter
}
