package com.seoulfitu.android.ui.sports_service_detail.uistate

import com.seoulfitu.android.ui.uimodel.UiSportsService

data class SportsServiceDetailUiState(
    val isSuccess:Boolean? = null,
    val result:UiSportsService = UiSportsService(),
    val errorMessage:String = ""
)