package com.seoulfitu.seoulfitu.ui.uimodel.mapper

import com.seoulfitu.seoulfitu.domain.model.SportsService
import com.seoulfitu.seoulfitu.domain.model.SportsServiceType
import com.seoulfitu.seoulfitu.ui.uimodel.UiSportsService
import com.seoulfitu.seoulfitu.ui.uimodel.UiSportsServiceInfo
import com.seoulfitu.seoulfitu.ui.uimodel.UiSportsServiceType

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
        yCoordinate = yCoordinate,
        address = address
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
        type = info.type.toDomain(),
        address = info.address
    )
}

private fun UiSportsServiceType.toDomain():SportsServiceType{
    return when(this){
        UiSportsServiceType.FUTSAL -> SportsServiceType.FUTSAL
        UiSportsServiceType.TENNIS -> SportsServiceType.TENNIS
        UiSportsServiceType.PING_PONG -> SportsServiceType.PING_PONG
        UiSportsServiceType.SOCCER -> SportsServiceType.SOCCER
        UiSportsServiceType.GYM -> SportsServiceType.GYM
        UiSportsServiceType.FOOT_VOLLEYBALL -> SportsServiceType.FOOT_VOLLEYBALL
        UiSportsServiceType.BASEBALL -> SportsServiceType.BASEBALL
        UiSportsServiceType.BADMINTON -> SportsServiceType.BADMINTON
        UiSportsServiceType.VOLLEYBALL -> SportsServiceType.VOLLEYBALL
        UiSportsServiceType.BASKETBALL -> SportsServiceType.BASKETBALL
        UiSportsServiceType.GOLF -> SportsServiceType.GOLF
        UiSportsServiceType.MULTI_PURPOSE_STADIUM -> SportsServiceType.MULTI_PURPOSE_STADIUM
        UiSportsServiceType.EDUCATIONAL_FACILITIES -> SportsServiceType.EDUCATIONAL_FACILITIES
        UiSportsServiceType.ETC -> SportsServiceType.ETC
    }
}

