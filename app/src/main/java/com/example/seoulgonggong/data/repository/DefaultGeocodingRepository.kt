package com.example.seoulgonggong.data.repository

import com.example.seoulgonggong.data.ERROR_MESSAGE_FAIL_RESULT
import com.example.seoulgonggong.data.ERROR_MESSAGE_NO_BODY
import com.example.seoulgonggong.data.model.mapper.toDomain
import com.example.seoulgonggong.data.service.GeocodingService
import com.example.seoulgonggong.data.service.ReverseGeocodingService
import com.example.seoulgonggong.domain.model.Addresses
import com.example.seoulgonggong.domain.model.Coordinate
import com.example.seoulgonggong.domain.model.Regions
import com.example.seoulgonggong.domain.repository.GeocodingRepository
import javax.inject.Inject

class DefaultGeocodingRepository @Inject constructor(
    private val geocodingService: GeocodingService, private val reverseGeocodingService: ReverseGeocodingService
) : GeocodingRepository {
    override suspend fun geocode(address: String): Result<Addresses> {
        val result = geocodingService.geocode(address)
        if (result.isSuccessful) {
            val body = result.body() ?: return Result.failure(IllegalStateException(ERROR_MESSAGE_NO_BODY))
            return Result.success(body.toDomain())
        } else {
            return Result.failure(IllegalStateException(ERROR_MESSAGE_FAIL_RESULT))
        }
    }

    override suspend fun reverseGeocode(coordinate: Coordinate): Result<Regions> {
        val coords = "${coordinate.x},${coordinate.y}"
        val result = reverseGeocodingService.reverseGeocode(coords)
        if (result.isSuccessful) {
            val body = result.body() ?: return Result.failure(IllegalStateException(ERROR_MESSAGE_NO_BODY))
            return Result.success(body.toDomain())
        } else {
            return Result.failure(IllegalStateException(ERROR_MESSAGE_FAIL_RESULT))
        }
    }
}