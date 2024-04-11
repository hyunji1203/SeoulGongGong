package com.example.seoulgonggong.data.repository

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.seoulgonggong.data.model.mapper.toDomain
import com.example.seoulgonggong.data.service.ParticulateMatterService
import com.example.seoulgonggong.domain.model.ParticulateMatter
import com.example.seoulgonggong.domain.repository.ParticulateMatterRepository

class DefaultParticulateMatterRepository(
    private val particulateMatterService: ParticulateMatterService,
) : ParticulateMatterRepository {
    override suspend fun getParticulateMatter(msrsteNm: String): ParticulateMatter {
        val response = particulateMatterService.getParticulateMatter(msrsteNm)
        if (response.isSuccessful) {
            return response.body()!!.toDomain()
        } else {
            Log.d("test", "else 문")
            throw NetworkErrorException("네트워크 오류")
        }
    }
}
