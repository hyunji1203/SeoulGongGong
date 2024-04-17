package com.seoulfitu.android.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodeResponse(
    @SerialName("status")
    val status: String,
    @SerialName("meta")
    val meta: GeocodeMeta,
    @SerialName("addresses")
    val addresses: List<GeocodeAddress>,
    @SerialName("errorMessage")
    val errorMessage: String
)

@Serializable
data class GeocodeMeta(
    @SerialName("totalCount")
    val totalCount: Int,
    @SerialName("page")
    val page: Int,
    @SerialName("count")
    val count: Int,
)

@Serializable
data class GeocodeAddress(
    @SerialName("roadAddress")
    val roadAddress: String,
    @SerialName("jibunAddress")
    val jibunAddress: String,
    @SerialName("englishAddress")
    val englishAddress: String,
    @SerialName("addressElements")
    val addressElements: List<GeocodeAddressElement>,
    @SerialName("x")
    val x: String,
    @SerialName("y")
    val y: String,
    @SerialName("distance")
    val distance: Double
)

@Serializable
data class GeocodeAddressElement(
    @SerialName("types")
    val types: List<String>,
    @SerialName("longName")
    val longName: String,
    @SerialName("shortName")
    val shortName: String,
    @SerialName("code")
    val code: String
)
