package com.seoulfitu.seoulfitu.data.repository

import com.seoulfitu.seoulfitu.data.local.dao.SportsFacilityScrapDao
import com.seoulfitu.seoulfitu.data.model.mapper.toDomain
import com.seoulfitu.seoulfitu.data.model.mapper.toEntity
import com.seoulfitu.seoulfitu.domain.model.SportsFacility
import com.seoulfitu.seoulfitu.domain.repository.FacilityScrapRepository
import javax.inject.Inject

class DefaultFacilityScrapRepository @Inject constructor(
    private val scrapDao: SportsFacilityScrapDao,
) : FacilityScrapRepository {
    override suspend fun getAll(): List<SportsFacility> {
        return scrapDao.getAll().map { SportsFacility(it.toDomain(), true) }
    }

    override suspend fun scrap(sportsFacility: SportsFacility) {
        scrapDao.insertScrap(sportsFacility.toEntity())
    }

    override suspend fun deleteScrap(sportsFacility: SportsFacility) {
        scrapDao.deleteScrap(sportsFacility.toEntity())
    }
}
