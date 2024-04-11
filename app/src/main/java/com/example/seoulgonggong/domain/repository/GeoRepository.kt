package com.example.seoulgonggong.domain.repository

interface GeoRepository {
    suspend fun getFullAddress(
        latitude: Double,
        longitude: Double,
    ): String
}
