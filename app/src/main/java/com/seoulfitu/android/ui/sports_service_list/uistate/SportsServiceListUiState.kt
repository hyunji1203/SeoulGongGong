package com.seoulfitu.android.ui.sports_service_list.uistate

import com.seoulfitu.android.ui.uimodel.UiSelectedOptions
import com.seoulfitu.android.ui.uimodel.UiSportsService


data class SportsServiceListUiState(
    val isSuccess: Boolean? = null,
    val result: List<UiSportsService> = listOf(),
    val errorMessage: String? = null,
    val selectedOptions: UiSelectedOptions = UiSelectedOptions()
)
