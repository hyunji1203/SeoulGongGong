package com.seoulfitu.android.domain.repository

import com.seoulfitu.android.domain.model.Addresses
import com.seoulfitu.android.domain.model.Coordinate
import com.seoulfitu.android.domain.model.RegionsWithCoordinate

interface GeocodingRepository {
    suspend fun geocode(address: String): Result<Addresses>
    suspend fun reverseGeocode(coordinate: Coordinate): Result<RegionsWithCoordinate>
}
