package com.seoulfitu.android.data.model.mapper

import com.seoulfitu.android.data.local.entity.SportsFacilityScrapEntity
import com.seoulfitu.android.domain.model.SportsFacility
import com.seoulfitu.android.domain.model.SportsFacilityInfo

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
        type = info.type.name,
        isOperating = info.isOperating,
        convenience = info.convenience,
        note = info.note,
    )
}
