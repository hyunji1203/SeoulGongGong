package com.seoulfitu.android.data.datasource

interface GeoDataSource {
    suspend fun getAddressByPosition(
        latitude: Double,
        longitude: Double,
    ): String
}
