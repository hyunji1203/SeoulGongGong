package com.seoulfitu.android.domain.repository

import com.seoulfitu.android.domain.model.SportsFacility

interface FacilityScrapRepository {
    suspend fun getAll(): List<SportsFacility>

    suspend fun scrap(sportsFacility: SportsFacility)

    suspend fun deleteScrap(sportsFacility: SportsFacility)
}
