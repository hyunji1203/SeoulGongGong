package com.seoulfitu.seoulfitu.domain.repository

import com.seoulfitu.seoulfitu.domain.model.SportsService

interface ServiceScrapRepository {
    suspend fun getAll(): List<SportsService>

    suspend fun scrap(sportsService: SportsService)

    suspend fun deleteScrap(sportsService: SportsService)
}
