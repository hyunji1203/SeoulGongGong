package com.example.seoulgonggong.domain.repository

import com.example.seoulgonggong.domain.model.ParticulateMatter

interface ParticulateMatterRepository {
    suspend fun getParticulateMatter(msrsteNm: String): ParticulateMatter
}
