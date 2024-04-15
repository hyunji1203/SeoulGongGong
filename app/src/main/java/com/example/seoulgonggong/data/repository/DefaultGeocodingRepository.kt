package com.example.seoulgonggong.data.repository

import com.example.seoulgonggong.data.ERROR_MESSAGE_FAIL_RESULT
import com.example.seoulgonggong.data.ERROR_MESSAGE_NO_BODY
import com.example.seoulgonggong.data.model.mapper.toDomain
import com.example.seoulgonggong.data.service.GeocodingService
import com.example.seoulgonggong.data.service.ReverseGeocodingService
import com.example.seoulgonggong.domain.model.Addresses
import com.example.seoulgonggong.domain.model.Regions
import com.example.seoulgonggong.domain.repository.GeocodingRepository
import javax.inject.Inject

class DefaultGeocodingRepository @Inject constructor(
    private val geocodingService: GeocodingService, private val reverseGeocodingService: ReverseGeocodingService
) : GeocodingRepository {
    override fun geocode(query: String): Result<Addresses> {
        val result = geocodingService.geocode(query)
        if (result.isSuccessful) {
            val body = result.body() ?: return Result.failure(IllegalStateException(ERROR_MESSAGE_NO_BODY))
            return Result.success(body.toDomain())
        } else {
            return Result.failure(IllegalStateException(ERROR_MESSAGE_FAIL_RESULT))
        }
    }

    override fun reverseGeocode(coords: String): Result<Regions> {
        val result = reverseGeocodingService.reverseGeocode(coords)
        if (result.isSuccessful) {
            val body = result.body() ?: return Result.failure(IllegalStateException(ERROR_MESSAGE_NO_BODY))
            return Result.success(body.toDomain())
        } else {
            return Result.failure(IllegalStateException(ERROR_MESSAGE_FAIL_RESULT))
        }
    }
}