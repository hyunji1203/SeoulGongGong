package com.seoulfitu.android.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.seoulfitu.android.data.local.dao.SportsFacilityScrapDao
import com.seoulfitu.android.data.local.entity.SportsFacilityScrapEntity

@Database(entities = [SportsFacilityScrapEntity::class], version = 1)
abstract class SportFacilityScrapDatabase : RoomDatabase() {
    abstract fun sportsFacilityScrapDao(): SportsFacilityScrapDao

    companion object {
        private var instance: SportFacilityScrapDatabase? = null

        @Synchronized
        fun getInstance(context: Context): SportFacilityScrapDatabase? {
            if (instance == null) {
                synchronized(SportFacilityScrapDatabase::class) {
                    instance =
                        Room.databaseBuilder(
                            context.applicationContext,
                            SportFacilityScrapDatabase::class.java,
                            "SportsFacilityScrap.db",
                        )
                            .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}
