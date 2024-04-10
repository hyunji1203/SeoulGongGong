package com.example.seoulgonggong.di

import com.example.seoulgonggong.data.repository.DefaultSportsFacilityRepository
import com.example.seoulgonggong.domain.repository.SportsFacilityRepository
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
}
