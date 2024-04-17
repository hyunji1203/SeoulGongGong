package com.example.seoulgonggong.domain.model

data class Address (
    val roadAddress: String,
    val jibunAddress: String,
    val englishAddress: String,
    val addressElements:List<AddressElement>,
    val coordinate: Coordinate,
    val distance:Double
)

data class AddressElement(
    val types:List<String>,
    val longName:String,
    val shortName:String,
    val code:String
)