package com.seoulfitu.android.domain.repository

import com.seoulfitu.android.domain.model.SportsService

interface ServiceScrapRepository {
    suspend fun getAll(): List<SportsService>

    suspend fun scrap(sportsService: SportsService)

    suspend fun deleteScrap(sportsService: SportsService)
}