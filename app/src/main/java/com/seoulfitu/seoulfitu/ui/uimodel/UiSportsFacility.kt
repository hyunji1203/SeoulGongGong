package com.seoulfitu.seoulfitu.ui.uimodel

import android.os.Parcelable
import com.naver.maps.map.overlay.Marker
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiSportsFacility(
    val isScrap: Boolean,
    val idx: Double,
    val facilityName: String,
    val facilityCategory: String,
    val address: String,
    val addressDetail: String,
    val phoneNumber: String,
    val operatingTimeWeekday: String,
    val operatingTimeWeekend: String,
    val operatingTimeHoliday: String,
    val money: String,
    val parkingInfo: String,
    val homepageUrl: String,
    val type: UiSportsFacilityType,
    val isOperating: String,
    val convenience: String,
    val canRental: String,
) : Parcelable

data class UiSportsFacilityList(
    val items: List<UiSportsFacility>,
    val isEmpty: Boolean = items.isEmpty(),
)

data class UiSportsFacilityWithCoordinate(
    val facility: UiSportsFacility,

    // 디폴트 좌표 : 서울 시청
    val x: Double = 37.5670135,
    val y: Double = 126.9783740,
)

data class UiSportsFacilityMarker(
    val marker: Marker,
    var facility: UiSportsFacility,
)
