package com.seoulfitu.android.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FacilityRow(
    @SerialName("FT_IDX")
    val idx: Double,
    @SerialName("AR_CD_NAME")
    val borough: String,
    @SerialName("FT_TITLE")
    val facilityName: String,
    @SerialName("BK_CD_NAME")
    val facilityCategory: String,
    @SerialName("FT_POST")
    val postNumber: String,
    @SerialName("FT_ADDR")
    val address: String,
    @SerialName("FT_ADDR_DETAIL")
    val addressDetail: String,
    @SerialName("FT_SIZE")
    val facilitySize: String,
    @SerialName("FT_ORG")
    val operatingOrganization: String,
    @SerialName("FT_PHONE")
    val phoneNumber: String,
    @SerialName("FT_WD_TIME")
    val operatingTimeWeekday: String,
    @SerialName("FT_WE_TIME")
    val operatingTimeWeekend: String,
    @SerialName("FT_INFO_TIME")
    val operatingTimeHoliday: String,
    @SerialName("RT_CD_NAME")
    val canRental: String,
    @SerialName("FT_MONEY")
    val money: String,
    @SerialName("FT_PARK")
    val parkingInfo: String,
    @SerialName("FT_HOMEPAGE")
    val homepageUrl: String,
    @SerialName("FT_KIND_NAME")
    val type: String,
    @SerialName("FT_OPERATION_NAME")
    val isOperating: String,
    @SerialName("FT_SI")
    val convenience: String,
    @SerialName("FT_BIGO")
    val note: String,
)
