package com.seoulfitu.seoulfitu.data.model.mapper

import com.seoulfitu.seoulfitu.data.model.response.SportsServiceResponse
import com.seoulfitu.seoulfitu.domain.model.SportsService
import com.seoulfitu.seoulfitu.domain.model.SportsServiceType
import com.seoulfitu.seoulfitu.domain.model.SportsServices


fun SportsServiceResponse.toDomain(): SportsServices = SportsServices(
    services = this.listPublicReservationSport.row.map {
      SportsService(
          division = it.division,
          serviceId = it.serviceId,
          mainCategory = it.mainCategory,
          type = changeServiceCategoryToType(it.subCategory),
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

private fun changeServiceCategoryToType(category:String):SportsServiceType{
    return when(category){
        "풋살장" -> SportsServiceType.FUTSAL
        "테니스장" -> SportsServiceType.TENNIS
        "탁구장" -> SportsServiceType.PING_PONG
        "축구장" -> SportsServiceType.SOCCER
        "체육관" -> SportsServiceType.GYM
        "족구장" -> SportsServiceType.FOOT_VOLLEYBALL
        "야구장"-> SportsServiceType.BASEBALL
        "배드민턴장" -> SportsServiceType.BADMINTON
        "배구장" -> SportsServiceType.VOLLEYBALL
        "농구장" -> SportsServiceType.BASKETBALL
        "골프장" -> SportsServiceType.GOLF
        "다목적경기장" -> SportsServiceType.MULTI_PURPOSE_STADIUM
        "교육시설" -> SportsServiceType.EDUCATIONAL_FACILITIES
        else -> SportsServiceType.ETC
    }
}
