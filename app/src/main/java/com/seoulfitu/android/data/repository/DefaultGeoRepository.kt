package com.seoulfitu.android.data.repository

import com.seoulfitu.android.domain.model.Town.Companion.findTownName
import com.seoulfitu.android.domain.repository.GeoRepository
import javax.inject.Inject

class DefaultGeoRepository
    @Inject
    constructor(
        private val geoDataSource: com.seoulfitu.android.data.datasource.GeoDataSource,
    ) : GeoRepository {
        override suspend fun getCityAddress(
            latitude: Double,
            longitude: Double,
        ): String {
            val address = geoDataSource.getAddressByPosition(latitude, longitude)
            return findTownName(address)
        }
    }
