package com.seoulfitu.seoulfitu.domain.repository

import com.seoulfitu.seoulfitu.domain.model.Addresses
import com.seoulfitu.seoulfitu.domain.model.Coordinate
import com.seoulfitu.seoulfitu.domain.model.RegionsWithCoordinate

interface GeocodingRepository {
    suspend fun geocode(address: String): Result<Addresses>
    suspend fun reverseGeocode(coordinate: Coordinate): Result<RegionsWithCoordinate>
}
