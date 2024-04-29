package com.seoulfitu.android.ui.uimodel.mapper

import com.seoulfitu.android.domain.model.SportsService
import com.seoulfitu.android.ui.uimodel.UiSportsService
import com.seoulfitu.android.ui.uimodel.UiSportsServiceInfo

fun SportsService.toUi(): UiSportsService = UiSportsService(
    info = UiSportsServiceInfo(
        serviceId = serviceId,
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
)

fun UiSportsService.toDomain(): SportsService {
    return SportsService(
        division = "",
        serviceId = info.serviceId,
        mainCategory = "",
        subCategory = info.subCategory,
        serviceStatus = info.status,
        serviceName = info.title,
        payment = info.payment,
        place = info.place,
        user = info.user,
        url = info.url,
        xCoordinate = info.xCoordinate,
        yCoordinate = info.yCoordinate,
        serviceStartDate = "",
        serviceEndDate = "",
        registrationStartDate = info.registrationStartDate,
        registrationEndDate = info.registrationEndDate,
        area = "",
        imgUrl = info.img,
        details = info.details,
        phoneNumber = info.phoneNumber,
        operatingStartTime = info.operatingStartTime,
        operatingEndTime = info.operatingEndTime,
        cancellationCriteria = "",
        timeLeftForCancellation = 0,
    )
}