package com.example.seoulgonggong.di

import com.example.seoulgonggong.data.datasource.GeoDataSource
import com.example.seoulgonggong.data.datasource.GoogleGeoDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    @Singleton
    fun bindGeoDatasource(dataSource: GoogleGeoDataSource): GeoDataSource
}
