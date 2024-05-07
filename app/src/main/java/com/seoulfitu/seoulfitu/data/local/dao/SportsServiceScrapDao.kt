package com.seoulfitu.seoulfitu.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seoulfitu.seoulfitu.data.local.entity.SportsServiceScrapEntity

@Dao
interface SportsServiceScrapDao {
    @Query("SELECT * FROM SportsServiceScrap")
    fun getAll(): List<SportsServiceScrapEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScrap(sportsService: SportsServiceScrapEntity)

    @Delete
    fun deleteScrap(sportsService: SportsServiceScrapEntity)
}
