package com.seoulfitu.seoulfitu.domain.repository

import com.seoulfitu.seoulfitu.domain.model.SportsFacility

interface FacilityScrapRepository {
    suspend fun getAll(): List<SportsFacility>

    suspend fun scrap(sportsFacility: SportsFacility)

    suspend fun deleteScrap(sportsFacility: SportsFacility)
}
