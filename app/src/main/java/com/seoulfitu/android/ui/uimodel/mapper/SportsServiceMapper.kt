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
        type = type.toUi(),
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

private fun SportsServiceType.toUi():UiSportsServiceType{
    return when(this){
        SportsServiceType.FUTSAL -> UiSportsServiceType.FUTSAL
        SportsServiceType.TENNIS -> UiSportsServiceType.TENNIS
        SportsServiceType.PING_PONG -> UiSportsServiceType.PING_PONG
        SportsServiceType.SOCCER -> UiSportsServiceType.SOCCER
        SportsServiceType.GYM -> UiSportsServiceType.GYM
        SportsServiceType.FOOT_VOLLEYBALL -> UiSportsServiceType.FOOT_VOLLEYBALL
        SportsServiceType.BASEBALL -> UiSportsServiceType.BASEBALL
        SportsServiceType.BADMINTON -> UiSportsServiceType.BADMINTON
        SportsServiceType.VOLLEYBALL -> UiSportsServiceType.VOLLEYBALL
        SportsServiceType.BASKETBALL -> UiSportsServiceType.BASKETBALL
        SportsServiceType.GOLF -> UiSportsServiceType.GOLF
        SportsServiceType.MULTI_PURPOSE_STADIUM -> UiSportsServiceType.MULTI_PURPOSE_STADIUM
        SportsServiceType.EDUCATIONAL_FACILITIES -> UiSportsServiceType.EDUCATIONAL_FACILITIES
        SportsServiceType.ETC-> UiSportsServiceType.ETC
    }
}

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

