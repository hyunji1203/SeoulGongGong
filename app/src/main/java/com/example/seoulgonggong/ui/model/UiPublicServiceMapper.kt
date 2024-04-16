package com.example.seoulgonggong.ui.model

import com.example.seoulgonggong.domain.model.PublicService
import com.example.seoulgonggong.domain.model.PublicServices


fun PublicServices.toUi(): List<UiPublicService> =
    this.services.map { it.toUi() }

fun PublicService.toUi(): UiPublicService = UiPublicService(
    title = serviceName,
    place = place,
    operatingTime = "$usageStartTime ~ $usageEndTime",
    img = imgUrl,
    status = serviceStatus,
)