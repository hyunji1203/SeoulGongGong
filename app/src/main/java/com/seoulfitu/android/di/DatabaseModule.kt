package com.seoulfitu.android.di

import android.content.Context
import androidx.room.Room
import com.seoulfitu.android.data.local.database.SportFacilityScrapDatabase
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
    ): SportFacilityScrapDatabase =
        Room.databaseBuilder(
            context,
            SportFacilityScrapDatabase::class.java,
            "SportsFacilityScrap",
        ).build()
}
