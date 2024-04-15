package com.example.seoulgonggong.data.repository

import com.example.seoulgonggong.data.service.GeocoderService
import com.example.seoulgonggong.data.utils.getResult
import com.example.seoulgonggong.domain.model.Addresses
import com.example.seoulgonggong.domain.model.Coordinate
import com.example.seoulgonggong.domain.model.RegionsWithCoordinate
import com.example.seoulgonggong.domain.repository.GeocodingRepository
import javax.inject.Inject

class DefaultGeocodingRepository @Inject constructor(private val service: GeocoderService) : GeocodingRepository {
    override suspend fun geocode(address: String): Result<Addresses> {
        val response = service.getGeocode(address)
        return getResult(response)
    }

    override suspend fun reverseGeocode(coordinate: Coordinate): Result<RegionsWithCoordinate> {
        val coords = "${coordinate.x},${coordinate.y}"
        val response = service.getReverseGeocode(coords)
        return getResult(response)
    }

}