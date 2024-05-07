package com.seoulfitu.seoulfitu.data.repository

import android.accounts.NetworkErrorException
import com.seoulfitu.seoulfitu.data.model.mapper.toDomain
import com.seoulfitu.seoulfitu.data.service.ParticulateMatterService
import com.seoulfitu.seoulfitu.domain.model.ParticulateMatter
import com.seoulfitu.seoulfitu.domain.repository.ParticulateMatterRepository
import javax.inject.Inject

class DefaultParticulateMatterRepository
    @Inject
    constructor(
        private val particulateMatterService: ParticulateMatterService,
    ) : ParticulateMatterRepository {
        override suspend fun getParticulateMatter(msrsteNm: String): ParticulateMatter {
            val responses = particulateMatterService.getParticulateMatter()
            if (responses.isSuccessful) {
                val cityAir =
                    responses.body()?.realTimeCityAir?.particulateMatterRow?.find {
                        msrsteNm == it.msrsteNm
                    } ?: responses.body()!!.realTimeCityAir.particulateMatterRow[0]
                return cityAir.toDomain()
            } else {
                throw NetworkErrorException()
            }
        }
    }
