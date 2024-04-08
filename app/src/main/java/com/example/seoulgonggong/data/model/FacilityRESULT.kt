package com.example.seoulgonggong.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FacilityRESULT(
    @SerialName("CODE")
    val cODE: String,
    @SerialName("MESSAGE")
    val mESSAGE: String
)
