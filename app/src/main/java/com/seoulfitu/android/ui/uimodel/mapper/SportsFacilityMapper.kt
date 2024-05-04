package com.seoulfitu.android.ui.uimodel.mapper

import com.seoulfitu.android.domain.model.SportsFacility
import com.seoulfitu.android.domain.model.SportsFacilityInfo
import com.seoulfitu.android.domain.model.SportsFacilityType
import com.seoulfitu.android.ui.uimodel.UiSportsFacility
import com.seoulfitu.android.ui.uimodel.UiSportsFacilityType

fun SportsFacility.toUi(isScraped: Boolean) =
    UiSportsFacility(
        isScrap = isScraped,
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
        canRental = info.canRental,
    )

fun UiSportsFacility.toDomain(isScraped: Boolean) =
    SportsFacility(
        SportsFacilityInfo(
            idx = idx,
            borough = "",
            facilityName = facilityName,
            facilityCategory = facilityCategory,
            postNumber = "",
            address = address,
            addressDetail = addressDetail,
            facilitySize = "",
            operatingOrganization = "",
            phoneNumber = phoneNumber,
            operatingTimeWeekday = operatingTimeWeekday,
            operatingTimeWeekend = operatingTimeWeekend,
            operatingTimeHoliday = operatingTimeHoliday,
            canRental = "",
            money = money,
            parkingInfo = parkingInfo,
            homepageUrl = homepageUrl,
            type = type.toDomain(),
            isOperating = isOperating,
            convenience = convenience,
            note = "",
        ),
        isScraped,
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

private fun UiSportsFacilityType.toDomain(): SportsFacilityType {
    return when (this) {
        UiSportsFacilityType.SWIMMING -> SportsFacilityType.SWIMMING
        UiSportsFacilityType.BASEBALL -> SportsFacilityType.BASEBALL
        UiSportsFacilityType.SOCCER -> SportsFacilityType.SOCCER
        UiSportsFacilityType.BASKETBALL -> SportsFacilityType.BASKETBALL
        UiSportsFacilityType.TENNIS -> SportsFacilityType.TENNIS
        UiSportsFacilityType.BADMINTON -> SportsFacilityType.BADMINTON
        UiSportsFacilityType.GOLF -> SportsFacilityType.GOLF
        UiSportsFacilityType.ICE_RINK -> SportsFacilityType.ICE_RINK
        UiSportsFacilityType.GATEBALL -> SportsFacilityType.GATEBALL
        UiSportsFacilityType.FOOT_VOLLEYBALL -> SportsFacilityType.FOOT_VOLLEYBALL
        UiSportsFacilityType.FUTSAL -> SportsFacilityType.FUTSAL
        UiSportsFacilityType.GYM -> SportsFacilityType.GYM
        UiSportsFacilityType.ETC -> SportsFacilityType.ETC
    }
}
