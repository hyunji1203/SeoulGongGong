package com.example.seoulgonggong.domain.model

data class PublicServices(
   val services:List<PublicService>
)

data class PublicService(
    val serviceName:String,
    val place:String,
    val usageStartTime:String,
    val usageEndTime:String,
    val serviceStatus:String
)