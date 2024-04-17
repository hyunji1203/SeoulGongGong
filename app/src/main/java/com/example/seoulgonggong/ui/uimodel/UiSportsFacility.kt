package com.example.seoulgonggong.ui.uimodel

import android.os.Parcelable
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
) : Parcelable

data class UiSportsFacilityList(
    val items: List<UiSportsFacility>,
    val isEmpty: Boolean = items.isEmpty(),
)
