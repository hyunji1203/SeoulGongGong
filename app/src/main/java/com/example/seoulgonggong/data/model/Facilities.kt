package com.example.seoulgonggong.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Facilities(
    @SerialName("list_total_count")
    val listTotalCount: Int,
    @SerialName("RESULT")
    val rESULT: FacilityRESULT,
    @SerialName("row")
    val row: List<FacilityRow>
)
