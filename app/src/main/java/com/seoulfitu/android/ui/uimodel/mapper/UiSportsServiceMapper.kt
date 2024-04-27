package com.seoulfitu.android.ui.uimodel.mapper

import com.seoulfitu.android.domain.model.SportsService
import com.seoulfitu.android.domain.model.SportsServiceType
import com.seoulfitu.android.domain.model.SportsServices
import com.seoulfitu.android.ui.uimodel.UiSportsService
import com.seoulfitu.android.ui.uimodel.UiSportsServiceInfo
import com.seoulfitu.android.ui.uimodel.UiSportsServiceType


fun SportsServices.toUi(): List<UiSportsService> =
    this.services.map { it.toUi() }

fun SportsService.toUi(): UiSportsService = UiSportsService(
    info = UiSportsServiceInfo(
        title = serviceName,
        place = place,
        operatingStartTime = operatingStartTime,
        operatingEndTime = operatingEndTime,
        img = imgUrl,
        status = serviceStatus,
        subCategory = subCategory,
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
        SportsServiceType.MULTI_PURPOSE_STADIUM,
        SportsServiceType.EDUCATIONAL_FACILITIES,
        SportsServiceType.ETC-> UiSportsServiceType.ETC
    }
}