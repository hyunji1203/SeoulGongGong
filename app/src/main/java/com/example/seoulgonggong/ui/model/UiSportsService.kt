package com.example.seoulgonggong.ui.model

import java.io.Serializable

data class UiSportsService(
    val title: String,
    val place: String,
    val usageStartTime:String,
    val usageEndTime:String,
    val img: String,
    val status: String,
    val subCategory:String,
    val address:String = "",
    val url:String,
    val phoneNumber:String,
    val registrationStartDate:String,
    val registrationEndDate:String,
    val user:String,
    val payment:String,
    val details:String
): Serializable