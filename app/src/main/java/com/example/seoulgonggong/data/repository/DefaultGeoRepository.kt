package com.example.seoulgonggong.data.repository

import com.example.seoulgonggong.data.datasource.GeoDataSource
import com.example.seoulgonggong.domain.model.Town.Companion.findTownName
import com.example.seoulgonggong.domain.repository.GeoRepository

class DefaultGeoRepository(
    private val geoDataSource: GeoDataSource,
) : GeoRepository {
    override suspend fun getCityAddress(
        latitude: Double,
        longitude: Double,
    ): String {
        val address = geoDataSource.getAddressByPosition(latitude, longitude)
        return findTownName(address)
    }
}
