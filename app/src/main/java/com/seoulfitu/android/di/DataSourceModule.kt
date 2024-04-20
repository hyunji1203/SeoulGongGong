package com.seoulfitu.android.di

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
    fun bindGeoDatasource(dataSource: com.seoulfitu.android.data.datasource.GoogleGeoDataSource): com.seoulfitu.android.data.datasource.GeoDataSource
}
