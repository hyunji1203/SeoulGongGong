package com.seoulfitu.android.ui.uimodel.mapper

import com.seoulfitu.android.domain.model.SportsFacility
import com.seoulfitu.android.domain.model.SportsFacilityType
import com.seoulfitu.android.ui.uimodel.UiSportsFacility
import com.seoulfitu.android.ui.uimodel.UiSportsFacilityType

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
    type = info.type.toUi(),
    isOperating = info.isOperating,
    convenience = info.convenience,
)

private fun SportsFacilityType.toUi(): UiSportsFacilityType {
    return when (this) {
        SportsFacilityType.SWIMMING -> UiSportsFacilityType.SWIMMING
        SportsFacilityType.BASEBALL -> UiSportsFacilityType.BASEBALL
        SportsFacilityType.SOCCER -> UiSportsFacilityType.SOCCER
        SportsFacilityType.BASKETBALL -> UiSportsFacilityType.BASKETBALL
        SportsFacilityType.TENNIS -> UiSportsFacilityType.TENNIS
        SportsFacilityType.BADMINTON -> UiSportsFacilityType.BADMINTON
        SportsFacilityType.GOLF -> UiSportsFacilityType.GOLF
        SportsFacilityType.ICE_RINK -> UiSportsFacilityType.ICE_RINK
        SportsFacilityType.GATEBALL -> UiSportsFacilityType.GATEBALL
        SportsFacilityType.FOOT_VOLLEYBALL -> UiSportsFacilityType.FOOT_VOLLEYBALL
        SportsFacilityType.FUTSAL -> UiSportsFacilityType.FUTSAL
        SportsFacilityType.GYM -> UiSportsFacilityType.GYM
        SportsFacilityType.ETC -> UiSportsFacilityType.ETC
    }
}
