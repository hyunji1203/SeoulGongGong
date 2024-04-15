package com.example.seoulgonggong.domain.repository

interface GeoRepository {
    suspend fun getCityAddress(
        latitude: Double,
        longitude: Double,
    ): String
}
