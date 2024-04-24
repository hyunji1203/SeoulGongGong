package com.seoulfitu.android.ui.uimodel.mapper

import com.seoulfitu.android.domain.model.SportsService
import com.seoulfitu.android.domain.model.SportsServices
import com.seoulfitu.android.ui.uimodel.UiSportsService
import com.seoulfitu.android.ui.uimodel.UiSportsServiceInfo


fun SportsServices.toUi(): List<UiSportsService> =
    this.services.map { it.toUi(false) }

fun SportsService.toUi(isScraped: Boolean): UiSportsService = UiSportsService(
    info = UiSportsServiceInfo(
        title = serviceName,
        place = place,
        operatingStartTime = operatingStartTime,
        operatingEndTime = operatingEndTime,
        img = imgUrl,
        status = serviceStatus,
        subCategory = subCategory,
        url = url,
        phoneNumber = phoneNumber,
        registrationStartDate = registrationStartDate,
        registrationEndDate = registrationEndDate,
        user = user,
        payment = payment,
        details = details,
        xCoordinate = xCoordinate,
        yCoordinate = yCoordinate
    ),
    isScraped,
)