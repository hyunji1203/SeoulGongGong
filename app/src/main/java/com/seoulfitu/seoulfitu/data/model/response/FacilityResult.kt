package com.seoulfitu.seoulfitu.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FacilityResult(
    @SerialName("CODE")
    val code: String,
    @SerialName("MESSAGE")
    val message: String
)
