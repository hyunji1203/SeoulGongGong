package com.example.seoulgonggong.di

import com.example.seoulgonggong.data.repository.DefaultDustRepository
import com.example.seoulgonggong.data.repository.DefaultWeatherRepository
import com.example.seoulgonggong.data.service.DustService
import com.example.seoulgonggong.data.service.WeatherService
import com.example.seoulgonggong.domain.repository.DustRepository
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
    fun provideDustRepository(dustService: DustService): DustRepository = DefaultDustRepository(dustService)
}
