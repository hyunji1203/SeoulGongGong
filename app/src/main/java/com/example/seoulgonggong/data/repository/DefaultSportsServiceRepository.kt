package com.example.seoulgonggong.data.repository

import com.example.seoulgonggong.data.model.toDomain
import com.example.seoulgonggong.data.service.SportsServiceService
import com.example.seoulgonggong.domain.model.SportsServices
import com.example.seoulgonggong.domain.repository.SportsServiceRepository
import javax.inject.Inject

class DefaultSportsServiceRepository @Inject constructor(private val sportsServiceService: SportsServiceService):SportsServiceRepository {
    override suspend fun getServices():Result<SportsServices>{
        val result = sportsServiceService.getServices()
        if (result.isSuccessful){
            val body = result.body()?.toDomain()
                ?: return Result.failure(IllegalStateException(ERROR_EMPTY_BODY_MESSAGE))
            return Result.success(body)
        }else{
            return Result.failure(IllegalStateException(ERROR_RESPONSE_FAIL_MESSAGE))
        }
    }

    companion object{
        private const val ERROR_EMPTY_BODY_MESSAGE = "응답 바디가 존재하지 않습니다."
        private const val ERROR_RESPONSE_FAIL_MESSAGE = "통신에 실패했습니다."
    }
}