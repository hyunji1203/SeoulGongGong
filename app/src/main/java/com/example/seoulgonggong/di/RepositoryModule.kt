package com.example.seoulgonggong.di

import com.example.seoulgonggong.data.repository.DefaultGeoRepository
import com.example.seoulgonggong.data.repository.DefaultParticulateMatterRepository
import com.example.seoulgonggong.data.repository.DefaultGeocodingRepository
import com.example.seoulgonggong.data.repository.DefaultSportsFacilityRepository
import com.example.seoulgonggong.data.repository.DefaultSportsServiceRepository
import com.example.seoulgonggong.data.repository.DefaultWeatherRepository
import com.example.seoulgonggong.domain.repository.GeoRepository
import com.example.seoulgonggong.domain.repository.ParticulateMatterRepository
import com.example.seoulgonggong.domain.repository.GeocodingRepository
import com.example.seoulgonggong.domain.repository.SportsFacilityRepository
import com.example.seoulgonggong.domain.repository.SportsServiceRepository
import com.example.seoulgonggong.domain.repository.WeatherRepository
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
    fun bindSportsServiceRepository(repository:DefaultSportsServiceRepository):SportsServiceRepository
}
