package com.seoulfitu.android.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SportsServiceResponse(
    @SerialName("ListPublicReservationSport") val listPublicReservationSport: ListPublicReservationSport
)

@Serializable
data class ListPublicReservationSport(
    @SerialName("list_total_count") val listTotalCount: Int,
    @SerialName("RESULT") val result: DataSportsServiceResult,
    @SerialName("row") val row: List<DataSportsServiceRow>
)

@Serializable
data class DataSportsServiceResult(
    @SerialName("CODE") val code: String, @SerialName("MESSAGE") val message: String
)

@Serializable
data class DataSportsServiceRow(
    @SerialName("GUBUN") val division: String,
    @SerialName("SVCID") val serviceId: String,
    @SerialName("MAXCLASSNM") val mainCategory: String,
    @SerialName("MINCLASSNM") val subCategory: String,
    @SerialName("SVCSTATNM") val serviceStatus: String,
    @SerialName("SVCNM") val serviceName: String,
    @SerialName("PAYATNM") val payment: String,
    @SerialName("PLACENM") val place: String,
    @SerialName("USETGTINFO") val user: String,
    @SerialName("SVCURL") val url: String,
    @SerialName("X") val xCoordinate: Double,
    @SerialName("Y") val yCoordinate: Double,
    @SerialName("SVCOPNBGNDT") val serviceStartDate: String,
    @SerialName("SVCOPNENDDT") val serviceEndDate: String,
    @SerialName("RCPTBGNDT") val registrationStartDate: String,
    @SerialName("RCPTENDDT") val registrationEndDate: String,
    @SerialName("AREANM") val area: String,
    @SerialName("IMGURL") val imgUrl: String,
    @SerialName("DTLCONT") val details: String,
    @SerialName("TELNO") val phoneNumber: String,
    @SerialName("V_MIN") val operatingStartTime: String,
    @SerialName("V_MAX") val operatingEndTime: String,
    @SerialName("REVSTDDAYNM") val cancellationCriteria: String,
    @SerialName("REVSTDDAY") val timeLeftForCancellation: Int
)
