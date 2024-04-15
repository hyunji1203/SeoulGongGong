package com.example.seoulgonggong.domain.repository

import com.example.seoulgonggong.domain.model.Addresses
import com.example.seoulgonggong.domain.model.Coordinate
import com.example.seoulgonggong.domain.model.Regions

interface GeocodingRepository {
    suspend fun geocode(address: String): Result<Addresses>
    suspend fun reverseGeocode(coordinate: Coordinate): Result<Regions>
}