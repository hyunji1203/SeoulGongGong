package com.seoulfitu.seoulfitu.data.datasource

interface GeoDataSource {
    suspend fun getAddressByPosition(
        latitude: Double,
        longitude: Double,
    ): String
}
