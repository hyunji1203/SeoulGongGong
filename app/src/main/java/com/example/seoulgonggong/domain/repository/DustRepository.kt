package com.example.seoulgonggong.domain.repository

import com.example.seoulgonggong.domain.model.Dust

interface DustRepository {
    suspend fun getDust(msrsteNm: String): Dust
}
