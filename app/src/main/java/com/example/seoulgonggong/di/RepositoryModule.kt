package com.example.seoulgonggong.di

import com.example.seoulgonggong.data.repository.DefaultSportsServiceRepository
import com.example.seoulgonggong.data.service.SportsServiceService
import com.example.seoulgonggong.domain.repository.SportsServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDefaultSportsServiceRepository(sportsServiceService: SportsServiceService): SportsServiceRepository {
        return DefaultSportsServiceRepository(sportsServiceService)
    }
}