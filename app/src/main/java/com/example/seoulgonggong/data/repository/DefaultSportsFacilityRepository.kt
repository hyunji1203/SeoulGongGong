package com.example.seoulgonggong.data.repository

import com.example.seoulgonggong.data.service.PublicSportsFacilityService
import com.example.seoulgonggong.data.model.mapper.toDomain
import com.example.seoulgonggong.domain.model.SportsFacility
import com.example.seoulgonggong.domain.repository.SportsFacilityRepository
import javax.inject.Inject

class DefaultSportsFacilityRepository @Inject constructor(
    private val sportsFacilityService: PublicSportsFacilityService,
) : SportsFacilityRepository {

    override fun getSportsFacility(): Result<List<SportsFacility>> =
        sportsFacilityService.getSportsFacility().map { result ->
            result.toDomain()
        }
}
