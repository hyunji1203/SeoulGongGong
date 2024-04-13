package com.example.seoulgonggong.ui.uimodel.mapper

import com.example.seoulgonggong.domain.model.SportsFacility
import com.example.seoulgonggong.ui.uimodel.UiSportsFacility

fun SportsFacility.toUi() = UiSportsFacility(
    isScrap = isScrap,
    idx = info.idx,
    facilityName = info.facilityName,
    facilityCategory = info.facilityCategory,
    address = info.address,
    addressDetail = info.addressDetail,
    phoneNumber = info.phoneNumber,
    operatingTimeWeekday = info.operatingTimeWeekday,
    operatingTimeWeekend = info.operatingTimeWeekend,
    operatingTimeHoliday = info.operatingTimeHoliday,
    money = info.money,
    parkingInfo = info.parkingInfo,
    homepageUrl = info.homepageUrl,
    type = info.type,
    isOperating = info.isOperating,
    convenience = info.convenience,
)
