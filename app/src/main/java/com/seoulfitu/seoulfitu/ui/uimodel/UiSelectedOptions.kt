package com.seoulfitu.seoulfitu.ui.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiSelectedOptions(
    val cities: List<String> = listOf(),
    val facilities: List<String> = listOf(),
    val services: List<String> = listOf(),
    val parking: UiAvailabilityFilter? = null,
    val rent: UiAvailabilityFilter? = null,
    val price: String = "",
    val serviceStatus: List<String> = listOf(),
) : Parcelable
