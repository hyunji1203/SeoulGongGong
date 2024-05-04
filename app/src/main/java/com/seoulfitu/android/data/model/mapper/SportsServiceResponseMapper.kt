package com.seoulfitu.android.data.model.mapper

import com.seoulfitu.android.data.model.response.SportsServiceResponse
import com.seoulfitu.android.domain.model.SportsService
import com.seoulfitu.android.domain.model.SportsServices


fun SportsServiceResponse.toDomain(): SportsServices = SportsServices(
    services = this.listPublicReservationSport.row.map {
      SportsService(
          division = it.division,
          serviceId = it.serviceId,
          mainCategory = it.mainCategory,
          subCategory = it.subCategory,
          serviceStatus = it.serviceStatus,
          serviceName = it.serviceName,
          payment = it.payment,
          place = it.place,
          user = it.user,
          url = it.url,
          xCoordinate = it.xCoordinate,
          yCoordinate = it.yCoordinate,
          serviceStartDate = it.serviceStartDate,
          serviceEndDate = it.serviceEndDate,
          registrationStartDate = it.registrationStartDate,
          registrationEndDate = it.registrationEndDate,
          area = it.area,
          imgUrl = it.imgUrl,
          details = it.details,
          phoneNumber = it.phoneNumber,
          operatingStartTime = it.operatingStartTime,
          operatingEndTime = it.operatingEndTime,
          cancellationCriteria = it.cancellationCriteria,
          timeLeftForCancellation = it.timeLeftForCancellation,
      )
    })