package com.seoulfitu.seoulfitu.data.repository

import com.seoulfitu.seoulfitu.data.ERROR_MESSAGE_FAIL_RESULT
import com.seoulfitu.seoulfitu.data.ERROR_MESSAGE_NO_BODY
import com.seoulfitu.seoulfitu.data.service.SportsFacilityService
import com.seoulfitu.seoulfitu.data.model.mapper.toDomain
import com.seoulfitu.seoulfitu.domain.model.SportsFacility
import com.seoulfitu.seoulfitu.domain.repository.SportsFacilityRepository
import javax.inject.Inject

class DefaultSportsFacilityRepository @Inject constructor(
    private val sportsFacilityService: SportsFacilityService,
) : SportsFacilityRepository {

    override suspend fun getSportsFacility(): Result<List<SportsFacility>> {
        val response = sportsFacilityService.getSportsFacility()
        return if (response.isSuccessful) {
            val body = response.body()
                ?: return Result.failure(IllegalStateException(ERROR_MESSAGE_NO_BODY))
            val result = body.facilities.row.filter { !it.facilityCategory.contains(EXCLUDE_WORD) }
                .map { SportsFacility(it.toDomain()) }
            Result.success(result)
        } else {
            Result.failure(IllegalStateException(ERROR_MESSAGE_FAIL_RESULT))
        }
    }

    companion object {
        private const val EXCLUDE_WORD = "학교"
    }
}
