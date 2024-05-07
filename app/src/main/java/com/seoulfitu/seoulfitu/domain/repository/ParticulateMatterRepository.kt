package com.seoulfitu.seoulfitu.domain.repository

import com.seoulfitu.seoulfitu.domain.model.ParticulateMatter

interface ParticulateMatterRepository {
    suspend fun getParticulateMatter(msrsteNm: String): ParticulateMatter
}
