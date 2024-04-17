package com.seoulfitu.android.data.repository

import com.seoulfitu.android.data.service.SportsFacilityService
import com.seoulfitu.android.data.model.mapper.toDomain
import com.seoulfitu.android.domain.model.SportsFacility
import com.seoulfitu.android.domain.repository.SportsFacilityRepository
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
