package com.example.seoulgonggong.di

import com.example.seoulgonggong.data.repository.DefaultPublicServiceRepository
import com.example.seoulgonggong.data.service.PublicServiceService
import com.example.seoulgonggong.domain.repository.PublicServiceRepository
import dagger.Provides
import javax.inject.Singleton

object RepositoryModule {
    @Provides
    @Singleton
    fun provideDefaultPublicServiceRepository(publicServiceService: PublicServiceService): PublicServiceRepository {
        return DefaultPublicServiceRepository(publicServiceService)
    }
}