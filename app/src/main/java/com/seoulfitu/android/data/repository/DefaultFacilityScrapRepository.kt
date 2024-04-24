package com.seoulfitu.android.data.repository

import com.seoulfitu.android.data.local.dao.SportsFacilityScrapDao
import com.seoulfitu.android.data.model.mapper.toDomain
import com.seoulfitu.android.data.model.mapper.toEntity
import com.seoulfitu.android.domain.model.SportsFacility
import com.seoulfitu.android.domain.repository.FacilityScrapRepository
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
