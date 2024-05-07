package com.seoulfitu.seoulfitu.data.repository

import com.seoulfitu.seoulfitu.data.local.dao.SportsServiceScrapDao
import com.seoulfitu.seoulfitu.data.model.mapper.toDomain
import com.seoulfitu.seoulfitu.data.model.mapper.toEntity
import com.seoulfitu.seoulfitu.domain.model.SportsService
import com.seoulfitu.seoulfitu.domain.repository.ServiceScrapRepository
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
