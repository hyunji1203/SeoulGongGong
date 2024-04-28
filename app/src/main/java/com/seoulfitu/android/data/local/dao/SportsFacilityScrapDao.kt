package com.seoulfitu.android.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seoulfitu.android.data.local.entity.SportsFacilityScrapEntity

@Dao
interface SportsFacilityScrapDao {
    @Query("SELECT * FROM SportsFacilityScrap")
    fun getAll(): List<SportsFacilityScrapEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScrap(sportsFacility: SportsFacilityScrapEntity)

    @Delete
    fun deleteScrap(sportsFacility: SportsFacilityScrapEntity)
}
