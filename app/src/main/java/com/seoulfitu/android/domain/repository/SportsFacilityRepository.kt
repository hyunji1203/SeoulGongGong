package com.seoulfitu.android.domain.repository

import com.seoulfitu.android.domain.model.SportsFacility

interface SportsFacilityRepository {

    suspend fun getSportsFacility(): Result<List<SportsFacility>>
}
