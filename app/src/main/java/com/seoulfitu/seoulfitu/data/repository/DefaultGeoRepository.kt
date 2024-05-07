package com.seoulfitu.seoulfitu.data.repository

import com.seoulfitu.seoulfitu.domain.model.Town.Companion.findTownName
import com.seoulfitu.seoulfitu.domain.repository.GeoRepository
import javax.inject.Inject

class DefaultGeoRepository
    @Inject
    constructor(
        private val geoDataSource: com.seoulfitu.seoulfitu.data.datasource.GeoDataSource,
    ) : GeoRepository {
        override suspend fun getCityAddress(
            latitude: Double,
            longitude: Double,
        ): String {
            val address = geoDataSource.getAddressByPosition(latitude, longitude)
            return findTownName(address)
        }
    }
