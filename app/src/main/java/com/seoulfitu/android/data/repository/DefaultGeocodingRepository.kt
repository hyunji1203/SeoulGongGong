package com.seoulfitu.android.data.repository

import com.seoulfitu.android.data.model.mapper.toDomain
import com.seoulfitu.android.data.service.GeocoderService
import com.seoulfitu.android.domain.model.Addresses
import com.seoulfitu.android.domain.model.Coordinate
import com.seoulfitu.android.domain.model.RegionsWithCoordinate
import com.seoulfitu.android.domain.repository.GeocodingRepository
import javax.inject.Inject

class DefaultGeocodingRepository @Inject constructor(private val service: GeocoderService) : GeocodingRepository {
    override suspend fun geocode(address: String): Result<Addresses> {
        val result = service.getGeocode(address)
        if (result.isSuccessful) {
            val body = result.body() ?: return Result.failure(IllegalStateException(com.seoulfitu.android.data.ERROR_MESSAGE_NO_BODY))
            return Result.success(body.toDomain())
        } else {
            return Result.failure(IllegalStateException(com.seoulfitu.android.data.ERROR_MESSAGE_FAIL_RESULT))
        }
    }

    override suspend fun reverseGeocode(coordinate: Coordinate): Result<RegionsWithCoordinate> {
        val coords = "${coordinate.x},${coordinate.y}"
        val result = service.getReverseGeocode(coords)
        if (result.isSuccessful) {
            val body = result.body() ?: return Result.failure(IllegalStateException(com.seoulfitu.android.data.ERROR_MESSAGE_NO_BODY))
            return Result.success(body.toDomain())
        } else {
            return Result.failure(IllegalStateException(com.seoulfitu.android.data.ERROR_MESSAGE_FAIL_RESULT))
        }
    }

}
