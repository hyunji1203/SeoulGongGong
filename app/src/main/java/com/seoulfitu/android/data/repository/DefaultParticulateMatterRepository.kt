package com.seoulfitu.android.data.repository

import android.accounts.NetworkErrorException
import com.seoulfitu.android.data.model.mapper.toDomain
import com.seoulfitu.android.data.service.ParticulateMatterService
import com.seoulfitu.android.domain.model.ParticulateMatter
import com.seoulfitu.android.domain.repository.ParticulateMatterRepository
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
