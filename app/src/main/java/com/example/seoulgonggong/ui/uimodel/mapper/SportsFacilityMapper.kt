package com.example.seoulgonggong.ui.uimodel.mapper

import com.example.seoulgonggong.domain.model.SportsFacility
import com.example.seoulgonggong.ui.uimodel.UiSportsFacility

fun SportsFacility.toUi() = UiSportsFacility(
    idx = idx,
    facilityName = facilityName,
    facilityCategory = facilityCategory,
    address = address,
    addressDetail = addressDetail,
    phoneNumber = phoneNumber,
    operatingTimeWeekday = operatingTimeWeekday,
    operatingTimeWeekend = operatingTimeWeekend,
    operatingTimeHoliday = operatingTimeHoliday,
    money = money,
    parkingInfo = parkingInfo,
    homepageUrl = homepageUrl,
    type = type,
    isOperating = isOperating,
    convenience = convenience,
)
