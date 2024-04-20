package com.seoulfitu.android.ui.uimodel.mapper

import com.seoulfitu.android.domain.model.SportsService
import com.seoulfitu.android.domain.model.SportsServices
import com.seoulfitu.android.ui.uimodel.UiSportsService


fun SportsServices.toUi(): List<UiSportsService> =
    this.services.map { it.toUi() }

fun SportsService.toUi(): UiSportsService = UiSportsService(
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
)