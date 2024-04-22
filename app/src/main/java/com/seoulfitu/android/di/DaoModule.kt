package com.seoulfitu.android.di

import com.seoulfitu.android.data.local.dao.SportsFacilityScrapDao
import com.seoulfitu.android.data.local.database.SportFacilityScrapDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun providesRecentSearchQueryDao(database: SportFacilityScrapDatabase): SportsFacilityScrapDao = database.sportsFacilityScrapDao()
}
