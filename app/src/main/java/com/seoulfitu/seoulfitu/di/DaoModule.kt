package com.seoulfitu.seoulfitu.di

import com.seoulfitu.seoulfitu.data.local.dao.SportsFacilityScrapDao
import com.seoulfitu.seoulfitu.data.local.dao.SportsServiceScrapDao
import com.seoulfitu.seoulfitu.data.local.database.SportsFacilityScrapDatabase
import com.seoulfitu.seoulfitu.data.local.database.SportsServiceScrapDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun providesSportsFacilityScrapDao(database: SportsFacilityScrapDatabase): SportsFacilityScrapDao = database.sportsFacilityScrapDao()

    @Provides
    fun providesSportsServiceScrapDao(database: SportsServiceScrapDatabase): SportsServiceScrapDao = database.sportsServiceScrapDao()
}
