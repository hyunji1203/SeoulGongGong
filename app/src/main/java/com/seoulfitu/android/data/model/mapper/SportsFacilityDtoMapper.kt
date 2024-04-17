package com.seoulfitu.android.data.model.mapper

import com.seoulfitu.android.data.model.response.FacilityRow
import com.seoulfitu.android.data.model.response.SportsFacilityResponse
import com.seoulfitu.android.domain.model.SportsFacility

fun SportsFacilityResponse.toDomain() = facilities.row.map { it.toDomain() }

private fun FacilityRow.toDomain() = SportsFacility(
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
    type = type,
    isOperating = isOperating,
    convenience = convenience,
    note = note,
)
