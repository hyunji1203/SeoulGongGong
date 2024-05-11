package com.seoulfitu.seoulfitu.di

import com.seoulfitu.seoulfitu.data.repository.DefaultFacilityScrapRepository
import com.seoulfitu.seoulfitu.data.repository.DefaultGeocodingRepository
import com.seoulfitu.seoulfitu.data.repository.DefaultParticulateMatterRepository
import com.seoulfitu.seoulfitu.data.repository.DefaultServiceScrapRepository
import com.seoulfitu.seoulfitu.data.repository.DefaultSportsFacilityRepository
import com.seoulfitu.seoulfitu.data.repository.DefaultSportsServiceRepository
import com.seoulfitu.seoulfitu.data.repository.DefaultWeatherRepository
import com.seoulfitu.seoulfitu.domain.repository.FacilityScrapRepository
import com.seoulfitu.seoulfitu.domain.repository.GeocodingRepository
import com.seoulfitu.seoulfitu.domain.repository.ParticulateMatterRepository
import com.seoulfitu.seoulfitu.domain.repository.ServiceScrapRepository
import com.seoulfitu.seoulfitu.domain.repository.SportsFacilityRepository
import com.seoulfitu.seoulfitu.domain.repository.SportsServiceRepository
import com.seoulfitu.seoulfitu.domain.repository.WeatherRepository
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
    fun bindSportsServiceRepository(repository:DefaultSportsServiceRepository): SportsServiceRepository

    @Binds
    @Singleton
    fun bindFacilityScrapRepository(repository: DefaultFacilityScrapRepository): FacilityScrapRepository

    @Binds
    @Singleton
    fun bindServiceScrapRepository(repository: DefaultServiceScrapRepository): ServiceScrapRepository
}
