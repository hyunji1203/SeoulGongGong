package com.seoulfitu.seoulfitu.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seoulfitu.seoulfitu.domain.model.SportsServiceType

@Entity(tableName = "SportsServiceScrap")
data class SportsServiceScrapEntity(
    val division: String,
    @PrimaryKey
    val serviceId: String,
    val mainCategory: String,
    val serviceStatus: String,
    val serviceName: String,
    val payment: String,
    val place: String,
    val user: String,
    val url: String,
    val xCoordinate: Double,
    val yCoordinate: Double,
    val serviceStartDate: String,
    val serviceEndDate: String,
    val registrationStartDate: String,
    val registrationEndDate: String,
    val area: String,
    val imgUrl: String,
    val details: String,
    val phoneNumber: String,
    val operatingStartTime: String,
    val operatingEndTime: String,
    val cancellationCriteria: String,
    val timeLeftForCancellation: Int,
    val type:SportsServiceType,
    val address:String
)
