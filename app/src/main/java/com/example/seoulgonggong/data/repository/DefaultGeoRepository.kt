package com.example.seoulgonggong.data.repository

import com.example.seoulgonggong.data.datasource.GeoDataSource
import com.example.seoulgonggong.domain.repository.GeoRepository

class DefaultGeoRepository(
    private val geoDataSource: GeoDataSource,
) : GeoRepository {
    override suspend fun getFullAddress(
        latitude: Double,
        longitude: Double,
    ): String {
        return geoDataSource.getAddressByPosition(latitude, longitude)
    }
}
