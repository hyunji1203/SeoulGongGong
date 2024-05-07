package com.seoulfitu.seoulfitu.ui.sports_service_list.uistate

import com.seoulfitu.seoulfitu.ui.uimodel.UiSelectedOptions
import com.seoulfitu.seoulfitu.ui.uimodel.UiSportsService


data class SportsServiceListUiState(
    val isSuccess: Boolean? = null,
    val result: List<UiSportsService> = listOf(),
    val errorMessage: String? = null,
    val selectedOptions: UiSelectedOptions = UiSelectedOptions()
)
