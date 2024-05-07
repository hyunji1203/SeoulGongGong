package com.seoulfitu.seoulfitu.domain.repository

interface GeoRepository {
    suspend fun getCityAddress(
        latitude: Double,
        longitude: Double,
    ): String
}
