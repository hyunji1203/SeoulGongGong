package com.example.seoulgonggong.data.repository

import com.example.seoulgonggong.data.model.toDomain
import com.example.seoulgonggong.data.service.SportsServiceService
import com.example.seoulgonggong.domain.model.PublicServices
import com.example.seoulgonggong.domain.repository.PublicServiceRepository
import javax.inject.Inject

class DefaultPublicServiceRepository @Inject constructor(private val sportsServiceService: SportsServiceService):PublicServiceRepository {
    override suspend fun getData():Result<PublicServices>{
        val result = sportsServiceService.getData()
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