package com.seoulfitu.android.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Facilities(
    @SerialName("list_total_count")
    val listTotalCount: Int,
    @SerialName("RESULT")
    val result: FacilityResult,
    @SerialName("row")
    val row: List<FacilityRow>
)
