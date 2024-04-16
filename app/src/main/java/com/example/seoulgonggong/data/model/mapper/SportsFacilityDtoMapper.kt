package com.example.seoulgonggong.data.model.mapper

import com.example.seoulgonggong.data.model.response.FacilityRow
import com.example.seoulgonggong.data.model.response.SportsFacilityResponse
import com.example.seoulgonggong.domain.model.SportsFacility

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
