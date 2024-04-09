package com.example.seoulgonggong.data.repository

import com.example.seoulgonggong.data.service.SportsFacilityService
import com.example.seoulgonggong.data.model.mapper.toDomain
import com.example.seoulgonggong.domain.model.SportsFacility
import com.example.seoulgonggong.domain.repository.SportsFacilityRepository
import javax.inject.Inject

class DefaultSportsFacilityRepository @Inject constructor(
    private val sportsFacilityService: SportsFacilityService,
) : SportsFacilityRepository {

    override suspend fun getSportsFacility(): Result<List<SportsFacility>> {
        val response = sportsFacilityService.getSportsFacility()
        return if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!.toDomain())
        } else {
            Result.failure(IllegalStateException("네트워크 오류가 발생했습니다"))
        }
    }
}
