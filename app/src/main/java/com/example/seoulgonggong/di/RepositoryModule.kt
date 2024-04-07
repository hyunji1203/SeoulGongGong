package com.example.seoulgonggong.di

import com.example.seoulgonggong.data.repository.DefaultWeatherRepository
import com.example.seoulgonggong.data.service.WeatherService
import com.example.seoulgonggong.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideWeatherRepository(weatherService: WeatherService): WeatherRepository = DefaultWeatherRepository(weatherService)
}
