package com.example.seoulgonggong.domain.model

data class PublicServices(
   val services:List<PublicService>
)

data class PublicService(
    val division: String,
    val serviceId: String,
    val mainCategory: String,
    val subCategory: String,
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
    val usageStartTime: String,
    val usageEndTime: String,
    val cancellationCriteria: String,
    val timeLeftForCancellation: Int
)