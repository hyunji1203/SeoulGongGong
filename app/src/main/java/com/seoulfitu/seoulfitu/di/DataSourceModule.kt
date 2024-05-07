package com.seoulfitu.seoulfitu.di

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
    fun bindGeoDatasource(dataSource: com.seoulfitu.seoulfitu.data.datasource.GoogleGeoDataSource): com.seoulfitu.seoulfitu.data.datasource.GeoDataSource
}
