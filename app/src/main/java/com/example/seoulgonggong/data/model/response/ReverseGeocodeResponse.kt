package com.example.seoulgonggong.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ReverseGeocodeResponse(
    @SerialName("status")
    val status: ReverseGeocodeStatus,
    @SerialName("results")
    val results: List<ReverseGeocodeResult>,
)

@Serializable
data class ReverseGeocodeStatus(
    @SerialName("code")
    val code: Int,
    @SerialName("name")
    val name: String,
    @SerialName("message")
    val message: String,
)

@Serializable
data class ReverseGeocodeResult(
    @SerialName("name")
    val name:String,
    @SerialName("code")
    val code:ReverseGeocodeCode,
    @SerialName("region")
    val region:ReverseGeocodeRegion
)

@Serializable
data class ReverseGeocodeCode(
    @SerialName("id")
    val id:String,
    @SerialName("type")
    val type:String,
    @SerialName("mappingId")
    val mappingId:String
)

@Serializable
data class ReverseGeocodeRegion(
    @SerialName("area0")
    val area0:ReverseGeocodeArea,
    @SerialName("area1")
    val area1:ReverseGeocodeArea,
    @SerialName("area2")
    val area2:ReverseGeocodeArea,
    @SerialName("area3")
    val area3:ReverseGeocodeArea,
    @SerialName("area4")
    val area4:ReverseGeocodeArea
)

@Serializable
data class ReverseGeocodeArea(
    @SerialName("name")
    val name:String,
    @SerialName("coords")
    val coords:ReverseGeocodeCoords,
    @SerialName("alias")
    val alias:String = ""
)

@Serializable
data class ReverseGeocodeCoords(
    @SerialName("center")
    val center:ReverseGeocodeCenter
)

@Serializable
data class ReverseGeocodeCenter(
    @SerialName("crs")
    val crs:String,
    @SerialName("x")
    val x:Double,
    @SerialName("y")
    val y:Double
)