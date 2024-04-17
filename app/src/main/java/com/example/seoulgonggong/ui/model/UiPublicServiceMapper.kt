package com.example.seoulgonggong.ui.model

import com.example.seoulgonggong.domain.model.SportsService
import com.example.seoulgonggong.domain.model.SportsServices


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
    details = details
)