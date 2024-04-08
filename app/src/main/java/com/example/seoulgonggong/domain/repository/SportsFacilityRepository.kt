package com.example.seoulgonggong.domain.repository

import com.example.seoulgonggong.domain.model.SportsFacility

interface SportsFacilityRepository {

    fun getSportsFacility(): Result<List<SportsFacility>>
}
