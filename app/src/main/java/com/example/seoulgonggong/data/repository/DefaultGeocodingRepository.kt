package com.example.seoulgonggong.data.repository

import com.example.seoulgonggong.data.model.response.GeocodeResponse
import com.example.seoulgonggong.data.model.response.ReverseGeocodeResponse
import com.example.seoulgonggong.data.service.GeocodingService
import com.example.seoulgonggong.data.service.ReverseGeocodingService
import com.example.seoulgonggong.domain.repository.GeocodingRepository
import javax.inject.Inject

class DefaultGeocodingRepository @Inject constructor(
    private val geocodingService: GeocodingService,
    private val reverseGeocodingService: ReverseGeocodingService
) : GeocodingRepository {
    override fun geocode(query: String): Result<GeocodeResponse> {
        TODO("Not yet implemented")
    }

    override fun reverseGeocode(coords: String): Result<ReverseGeocodeResponse> {
        TODO("Not yet implemented")
    }
}