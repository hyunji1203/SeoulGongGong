package com.example.seoulgonggong.ui.feature.sports_service_list.uistate

import com.example.seoulgonggong.ui.model.UiSportsService

data class PublicServiceListUiState(
    val isSuccess: Boolean? = null,
    val result: List<UiSportsService> = listOf(),
    val errorMessage: String? = null
)