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
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.toDomain())
        } else if (response.body() == null) {
            Result.failure(IllegalStateException(NULL_BODY_ERROR_MESSAGE))
        } else {
            Result.failure(IllegalStateException(NETWORK_ERROR_MESSAGE))
        }
    }

    companion object {
        private const val NULL_BODY_ERROR_MESSAGE = "응답 바디가 존재하지 않습니다"
        private const val NETWORK_ERROR_MESSAGE = "네트워크 오류가 발생했습니다"
    }
}
