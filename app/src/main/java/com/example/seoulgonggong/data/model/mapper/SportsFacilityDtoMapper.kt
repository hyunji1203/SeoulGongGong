package com.example.seoulgonggong.data.model.mapper

import com.example.seoulgonggong.data.model.FacilityRow
import com.example.seoulgonggong.data.model.SportsFacilityResponse
import com.example.seoulgonggong.domain.model.SportsFacility
import com.example.seoulgonggong.domain.model.SportsFacilityInfo

fun SportsFacilityResponse.toDomain() = facilities.row.map { SportsFacility(it.toDomain()) }


private fun FacilityRow.toDomain() = SportsFacilityInfo(
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
