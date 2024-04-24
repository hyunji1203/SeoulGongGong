package com.seoulfitu.android.di

import com.seoulfitu.android.data.repository.DefaultFacilityScrapRepository
import com.seoulfitu.android.data.repository.DefaultGeoRepository
import com.seoulfitu.android.data.repository.DefaultGeocodingRepository
import com.seoulfitu.android.data.repository.DefaultParticulateMatterRepository
import com.seoulfitu.android.data.repository.DefaultSportsFacilityRepository
import com.seoulfitu.android.data.repository.DefaultSportsServiceRepository
import com.seoulfitu.android.data.repository.DefaultWeatherRepository
import com.seoulfitu.android.domain.repository.FacilityScrapRepository
import com.seoulfitu.android.domain.repository.GeoRepository
import com.seoulfitu.android.domain.repository.GeocodingRepository
import com.seoulfitu.android.domain.repository.ParticulateMatterRepository
import com.seoulfitu.android.domain.repository.SportsFacilityRepository
import com.seoulfitu.android.domain.repository.SportsServiceRepository
import com.seoulfitu.android.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindsSportsFacilityRepository(repository: DefaultSportsFacilityRepository): SportsFacilityRepository

    @Binds
    @Singleton
    fun bindWeatherRepository(repository: DefaultWeatherRepository): WeatherRepository

    @Binds
    @Singleton
    fun bindParticulateMatterRepository(repository: DefaultParticulateMatterRepository): ParticulateMatterRepository

    @Binds
    @Singleton
    fun bindsGeocodingRepository(repository: DefaultGeocodingRepository): GeocodingRepository

    @Binds
    @Singleton
    fun bindGeoRepository(repository: DefaultGeoRepository): GeoRepository

    @Binds
    @Singleton
    fun bindSportsServiceRepository(repository:DefaultSportsServiceRepository): SportsServiceRepository

    @Binds
    @Singleton
    fun bindFacilityScrapRepository(repository: DefaultFacilityScrapRepository): FacilityScrapRepository
}
