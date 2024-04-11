package com.example.seoulgonggong.data.repository

import android.accounts.NetworkErrorException
import com.example.seoulgonggong.data.model.mapper.toDomain
import com.example.seoulgonggong.data.service.ParticulateMatterService
import com.example.seoulgonggong.domain.model.ParticulateMatter
import com.example.seoulgonggong.domain.repository.ParticulateMatterRepository

class DefaultParticulateMatterRepository(
    private val particulateMatterService: ParticulateMatterService,
) : ParticulateMatterRepository {
    override suspend fun getParticulateMatter(msrsteNm: String): ParticulateMatter {
        val responses = particulateMatterService.getParticulateMatter()
        if (responses.isSuccessful) {
            val cityAir =
                responses.body()?.realTimeCityAir?.row?.find {
                    msrsteNm == it.msrsteNm
                } ?: responses.body()!!.realTimeCityAir.row[0]
            return cityAir.toDomain()
        } else {
            throw NetworkErrorException()
        }
    }
}
