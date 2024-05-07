package com.seoulfitu.seoulfitu.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seoulfitu.seoulfitu.data.local.dao.SportsServiceScrapDao
import com.seoulfitu.seoulfitu.data.local.entity.SportsServiceScrapEntity

@Database(entities = [SportsServiceScrapEntity::class], version = 1)
abstract class SportsServiceScrapDatabase : RoomDatabase() {
    abstract fun sportsServiceScrapDao(): SportsServiceScrapDao
}
