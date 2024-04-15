package com.example.seoulgonggong.domain.repository

import com.example.seoulgonggong.domain.model.Addresses
import com.example.seoulgonggong.domain.model.Regions

interface GeocodingRepository {
    fun geocode(query: String): Result<Addresses>
    fun reverseGeocode(coords: String): Result<Regions>
}