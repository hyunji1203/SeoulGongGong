package com.seoulfitu.seoulfitu.data.model.mapper

import com.seoulfitu.seoulfitu.data.model.response.FacilityRow
import com.seoulfitu.seoulfitu.domain.model.SportsFacilityInfo
import com.seoulfitu.seoulfitu.domain.model.SportsFacilityType

fun FacilityRow.toDomain() =
    SportsFacilityInfo(
        idx = idx,
        borough = borough,
        facilityName = facilityName,
        facilityCategory = facilityCategory,
        postNumber = postNumber,
        address = address,
        addressDetail = addressDetail,
        facilitySize = facilitySize,
        operatingOrganization = operatingOrganization,
        phoneNumber = phoneNumber,
        operatingTimeWeekday = operatingTimeWeekday,
        operatingTimeWeekend = operatingTimeWeekend,
        operatingTimeHoliday = operatingTimeHoliday,
        canRental = canRental,
        money = money,
        parkingInfo = parkingInfo,
        homepageUrl = homepageUrl,
        type = changeFacilityTypeToDomain(type),
        isOperating = isOperating,
        convenience = convenience,
        note = note,
    )

fun changeFacilityTypeToDomain(type: String): SportsFacilityType {
    return when (type) {
        "수영장" -> SportsFacilityType.SWIMMING
        "야구장" -> SportsFacilityType.BASEBALL
        "축구장" -> SportsFacilityType.SOCCER
        "농구장" -> SportsFacilityType.BASKETBALL
        "테니스장" -> SportsFacilityType.TENNIS
        "배트민턴장" -> SportsFacilityType.BADMINTON
        "골프연습장" -> SportsFacilityType.GOLF
        "빙상장" -> SportsFacilityType.ICE_RINK
        "게이트볼" -> SportsFacilityType.GATEBALL
        "족구장" -> SportsFacilityType.FOOT_VOLLEYBALL
        "풋살장" -> SportsFacilityType.FUTSAL
        "생활체육관" -> SportsFacilityType.GYM
        else -> SportsFacilityType.ETC
    }
}
