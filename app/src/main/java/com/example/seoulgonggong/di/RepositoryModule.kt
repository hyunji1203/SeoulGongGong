package com.example.seoulgonggong.di

import com.example.seoulgonggong.data.repository.DefaultPublicServiceRepository
import com.example.seoulgonggong.data.service.SportsServiceService
import com.example.seoulgonggong.domain.repository.PublicServiceRepository
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
    fun provideDefaultPublicServiceRepository(sportsServiceService: SportsServiceService): PublicServiceRepository {
        return DefaultPublicServiceRepository(sportsServiceService)
    }
}