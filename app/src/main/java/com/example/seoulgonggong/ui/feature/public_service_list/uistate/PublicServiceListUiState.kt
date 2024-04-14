package com.example.seoulgonggong.ui.feature.public_service_list.uistate

import com.example.seoulgonggong.ui.model.UiPublicService

data class PublicServiceListUiState(
    val isSuccess: Boolean? = null,
    val result: List<UiPublicService> = listOf(),
    val errorMessage: String? = null
)