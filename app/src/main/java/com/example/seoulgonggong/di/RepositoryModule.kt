package com.example.seoulgonggong.di

import com.example.seoulgonggong.data.datasource.GeoDataSource
import com.example.seoulgonggong.data.repository.DefaultGeoRepository
import com.example.seoulgonggong.data.repository.DefaultParticulateMatterRepository
import com.example.seoulgonggong.data.repository.DefaultWeatherRepository
import com.example.seoulgonggong.data.service.ParticulateMatterService
import com.example.seoulgonggong.data.service.WeatherService
import com.example.seoulgonggong.domain.repository.GeoRepository
import com.example.seoulgonggong.domain.repository.ParticulateMatterRepository
import com.example.seoulgonggong.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideWeatherRepository(weatherService: WeatherService): WeatherRepository = DefaultWeatherRepository(weatherService)

    @Provides
    @Singleton
    fun provideParticulateMatterRepository(particulateMatterService: ParticulateMatterService): ParticulateMatterRepository =
        DefaultParticulateMatterRepository(particulateMatterService)

    @Provides
    @Singleton
    fun provideAGeoRepository(geoDataSource: GeoDataSource): GeoRepository = DefaultGeoRepository(geoDataSource)
}
