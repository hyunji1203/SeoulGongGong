package com.example.seoulgonggong.domain.model

import kotlinx.serialization.Serializable

data class RegionWithCoordinate(
    val area0: Area,
    val area1: Area,
    val area2: Area,
    val area3: Area,
    val area4: Area
)

data class Area(
    val name:String,
    val coords: Coords,
    val alias:String = ""
)

@Serializable
data class Coords(
    val crs:String,
    val x:Double,
    val y:Double
)