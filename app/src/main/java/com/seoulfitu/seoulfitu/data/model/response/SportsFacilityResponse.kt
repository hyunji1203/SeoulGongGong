package com.seoulfitu.seoulfitu.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SportsFacilityResponse(
    @SerialName("facilities")
    val facilities: Facilities
)
