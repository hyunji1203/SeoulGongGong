package com.seoulfitu.seoulfitu.domain.repository

import com.seoulfitu.seoulfitu.domain.model.SportsFacility

interface SportsFacilityRepository {

    suspend fun getSportsFacility(): Result<List<SportsFacility>>
}
