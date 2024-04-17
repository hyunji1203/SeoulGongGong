package com.seoulfitu.android.domain.repository

interface GeoRepository {
    suspend fun getCityAddress(
        latitude: Double,
        longitude: Double,
    ): String
}
