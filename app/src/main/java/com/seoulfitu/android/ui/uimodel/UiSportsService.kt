package com.seoulfitu.android.ui.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiSportsService(
    val info: UiSportsServiceInfo = UiSportsServiceInfo(),
    val scrapped: Boolean = false
):Parcelable

@Parcelize
data class UiSportsServiceInfo(
    val title: String = "",
    val place: String = "",
    val operatingStartTime: String = "",
    val operatingEndTime: String = "",
    val img: String = "",
    val status: String = "",
    val subCategory: String = "",
    val address: String = "",
    val url: String = "",
    val phoneNumber: String = "",
    val registrationStartDate: String = "",
    val registrationEndDate: String = "",
    val user: String = "",
    val payment: String = "",
    val details: String = "",
    val xCoordinate: Double = 0.0,
    val yCoordinate: Double = 0.0,
) :Parcelable