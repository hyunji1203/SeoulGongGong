package com.seoulfitu.android.data.repository

import com.seoulfitu.android.data.local.dao.SportsServiceScrapDao
import com.seoulfitu.android.data.model.mapper.toDomain
import com.seoulfitu.android.data.model.mapper.toEntity
import com.seoulfitu.android.domain.model.SportsService
import com.seoulfitu.android.domain.repository.ServiceScrapRepository
import javax.inject.Inject

class DefaultServiceScrapRepository @Inject constructor(
    private val scrapDao: SportsServiceScrapDao,
) : ServiceScrapRepository {
    override suspend fun getAll(): List<SportsService> {
        return scrapDao.getAll().map { it.toDomain() }
    }

    override suspend fun scrap(sportsService: SportsService) {
        scrapDao.insertScrap(sportsService.toEntity())
    }

    override suspend fun deleteScrap(sportsService: SportsService) {
        scrapDao.deleteScrap(sportsService.toEntity())
    }
}
