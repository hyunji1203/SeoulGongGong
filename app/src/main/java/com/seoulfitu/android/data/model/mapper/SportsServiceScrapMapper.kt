package com.seoulfitu.android.data.model.mapper

import com.seoulfitu.android.data.local.entity.SportsServiceScrapEntity
import com.seoulfitu.android.domain.model.SportsService

fun SportsServiceScrapEntity.toDomain(): SportsService {
    return SportsService(
        division = division,
        serviceId = serviceId,
        mainCategory = mainCategory,
        subCategory = subCategory,
        serviceStatus = serviceStatus,
        serviceName = serviceName,
        payment = payment,
        place = place,
        user = user,
        url = url,
        xCoordinate = xCoordinate,
        yCoordinate = yCoordinate,
        serviceStartDate = serviceStartDate,
        serviceEndDate = serviceEndDate,
        registrationStartDate = registrationStartDate,
        registrationEndDate = registrationEndDate,
        area = area,
        imgUrl = imgUrl,
        details = details,
        phoneNumber = phoneNumber,
        operatingStartTime = operatingStartTime,
        operatingEndTime = operatingEndTime,
        cancellationCriteria = cancellationCriteria,
        timeLeftForCancellation = timeLeftForCancellation,
        address = address
    )
}

fun SportsService.toEntity(): SportsServiceScrapEntity {
    return SportsServiceScrapEntity(
        division = division,
        serviceId = serviceId,
        mainCategory = mainCategory,
        subCategory = subCategory,
        serviceStatus = serviceStatus,
        serviceName = serviceName,
        payment = payment,
        place = place,
        user = user,
        url = url,
        xCoordinate = xCoordinate,
        yCoordinate = yCoordinate,
        serviceStartDate = serviceStartDate,
        serviceEndDate = serviceEndDate,
        registrationStartDate = registrationStartDate,
        registrationEndDate = registrationEndDate,
        area = area,
        imgUrl = imgUrl,
        details = details,
        phoneNumber = phoneNumber,
        operatingStartTime = operatingStartTime,
        operatingEndTime = operatingEndTime,
        cancellationCriteria = cancellationCriteria,
        timeLeftForCancellation = timeLeftForCancellation,
        address = address
    )
}