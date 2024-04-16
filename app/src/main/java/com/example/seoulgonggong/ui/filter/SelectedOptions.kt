package com.example.seoulgonggong.ui.filter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectedOptions(
    val cities: List<String>,
    val facilities: List<String> = listOf(),
    val services: List<String> = listOf(),
    val parking: List<String>,
    val rent: List<String> = listOf(),
    val price: List<String> = listOf(),
    val serviceStatus: List<String> = listOf(),
) : Parcelable
