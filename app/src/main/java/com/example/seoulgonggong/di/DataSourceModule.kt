package com.example.seoulgonggong.di

import android.content.Context
import com.example.seoulgonggong.data.datasource.GeoDataSource
import com.example.seoulgonggong.data.datasource.GoogleGeoDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Singleton
    @Provides
    fun provideGeoDatasource(
        @ApplicationContext context: Context,
    ): GeoDataSource = GoogleGeoDataSource(context)
}
