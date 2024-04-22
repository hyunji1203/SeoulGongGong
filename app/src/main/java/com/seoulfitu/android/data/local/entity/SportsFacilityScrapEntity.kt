package com.seoulfitu.android.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SCRAP_FACILITY_TABLE")
data class SportsFacilityScrapEntity(
    @PrimaryKey
    val idx: Double = 0.0,
    val borough: String = "",
    val facilityName: String = "",
    val facilityCategory: String = "",
    val postNumber: String = "",
    val address: String = "",
    val addressDetail: String = "",
    val facilitySize: String = "",
    val operatingOrganization: String = "",
    val phoneNumber: String = "",
    val operatingTimeWeekday: String = "",
    val operatingTimeWeekend: String = "",
    val operatingTimeHoliday: String = "",
    val canRental: String = "",
    val money: String = "",
    val parkingInfo: String = "",
    val homepageUrl: String = "",
    val type: String = "",
    val isOperating: String = "",
    val convenience: String = "",
    val note: String = "",
)
