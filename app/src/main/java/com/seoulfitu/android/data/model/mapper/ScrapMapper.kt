package com.seoulfitu.android.data.model.mapper

import com.seoulfitu.android.data.local.entity.SportsFacilityScrapEntity
import com.seoulfitu.android.domain.model.SportsFacility
import com.seoulfitu.android.domain.model.SportsFacilityInfo
import com.seoulfitu.android.domain.model.SportsFacilityType

fun SportsFacilityScrapEntity.toDomain(): SportsFacilityInfo {
    return SportsFacilityInfo(
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
}

fun SportsFacility.toEntity(): SportsFacilityScrapEntity {
    return SportsFacilityScrapEntity(
        idx = info.idx,
        borough = info.borough,
        facilityName = info.facilityName,
        facilityCategory = info.facilityCategory,
        postNumber = info.postNumber,
        address = info.address,
        addressDetail = info.addressDetail,
        facilitySize = info.facilitySize,
        operatingOrganization = info.operatingOrganization,
        phoneNumber = info.phoneNumber,
        operatingTimeWeekday = info.operatingTimeWeekday,
        operatingTimeWeekend = info.operatingTimeWeekend,
        operatingTimeHoliday = info.operatingTimeHoliday,
        canRental = info.canRental,
        money = info.money,
        parkingInfo = info.parkingInfo,
        homepageUrl = info.homepageUrl,
        type = changeFacilityTypeToEntity(info.type),
        isOperating = info.isOperating,
        convenience = info.convenience,
        note = info.note,
    )
}

fun changeFacilityTypeToEntity(type: SportsFacilityType): String {
    return when (type) {
        SportsFacilityType.SWIMMING -> "수영장"
        SportsFacilityType.BASEBALL -> "야구장"
        SportsFacilityType.SOCCER -> "축구장"
        SportsFacilityType.BASKETBALL -> "농구장"
        SportsFacilityType.TENNIS -> "테니스장"
        SportsFacilityType.BADMINTON -> "배트민턴장"
        SportsFacilityType.GOLF -> "골프연습장"
        SportsFacilityType.ICE_RINK -> "빙상장"
        SportsFacilityType.GATEBALL -> "게이트볼"
        SportsFacilityType.FOOT_VOLLEYBALL -> "족구장"
        SportsFacilityType.FUTSAL -> "풋살장"
        SportsFacilityType.GYM -> "생활체육관"
        SportsFacilityType.ETC -> "기타"
    }
}
