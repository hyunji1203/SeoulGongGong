package com.example.seoulgonggong.data.repository

import com.example.seoulgonggong.data.service.PublicSportsFacilityService
import com.example.seoulgonggong.data.model.mapper.toDomain
import com.example.seoulgonggong.domain.model.SportsFacility
import com.example.seoulgonggong.domain.repository.SportsFacilityRepository
import javax.inject.Inject

class DefaultSportsFacilityRepository @Inject constructor(
    private val sportsFacilityService: PublicSportsFacilityService,
) : SportsFacilityRepository {

    override suspend fun getSportsFacility(): Result<List<SportsFacility>> {
        val a = sportsFacilityService.getSportsFacility()
        if (a.isSuccessful && a.body() != null) {
            return Result.success(
                a.body()!!.toDomain()
            )
        } else throw IllegalStateException("네트워크 오류가 발생했습니다")
    }
}
