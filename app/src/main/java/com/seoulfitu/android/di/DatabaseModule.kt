package com.seoulfitu.android.di

import android.content.Context
import androidx.room.Room
import com.seoulfitu.android.data.local.database.SportsFacilityScrapDatabase
import com.seoulfitu.android.data.local.database.SportsServiceScrapDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesFacilityScrapDatabase(
        @ApplicationContext context: Context,
    ): SportsFacilityScrapDatabase =
        Room.databaseBuilder(
            context,
            SportsFacilityScrapDatabase::class.java,
            "SportsFacilityScrap",
        ).build()

    @Provides
    @Singleton
    fun providesServiceScrapDatabase(
        @ApplicationContext context: Context,
    ): SportsServiceScrapDatabase =
        Room.databaseBuilder(
            context,
            SportsServiceScrapDatabase::class.java,
            "SportsServiceScrap",
        ).build()
}
