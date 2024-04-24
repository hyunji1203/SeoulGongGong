package com.seoulfitu.android.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seoulfitu.android.data.local.dao.SportsFacilityScrapDao
import com.seoulfitu.android.data.local.entity.SportsFacilityScrapEntity

@Database(entities = [SportsFacilityScrapEntity::class], version = 1)
abstract class SportsFacilityScrapDatabase : RoomDatabase() {
    abstract fun sportsFacilityScrapDao(): SportsFacilityScrapDao
}
