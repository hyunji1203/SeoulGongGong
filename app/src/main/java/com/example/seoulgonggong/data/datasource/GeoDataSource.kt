package com.example.seoulgonggong.data.datasource

interface GeoDataSource {
    suspend fun getAddressByPosition(
        latitude: Double,
        longitude: Double,
    ): String
}
