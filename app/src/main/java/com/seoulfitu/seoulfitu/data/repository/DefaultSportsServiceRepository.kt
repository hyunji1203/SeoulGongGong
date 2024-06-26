package com.seoulfitu.seoulfitu.data.repository

import com.seoulfitu.seoulfitu.data.ERROR_MESSAGE_FAIL_RESULT
import com.seoulfitu.seoulfitu.data.ERROR_MESSAGE_NO_BODY
import com.seoulfitu.seoulfitu.data.model.mapper.toDomain
import com.seoulfitu.seoulfitu.data.service.SportsServiceService
import com.seoulfitu.seoulfitu.domain.model.SportsServices
import com.seoulfitu.seoulfitu.domain.repository.SportsServiceRepository
import javax.inject.Inject

class DefaultSportsServiceRepository @Inject constructor(private val sportsServiceService: SportsServiceService):
    SportsServiceRepository {
    override suspend fun getServices():Result<SportsServices>{
        val result = sportsServiceService.getServices()
        if (result.isSuccessful){
            val body = result.body()?.toDomain()
                ?: return Result.failure(IllegalStateException(ERROR_MESSAGE_NO_BODY))
            return Result.success(body)
        }else{
            return Result.failure(IllegalStateException(ERROR_MESSAGE_FAIL_RESULT))
        }
    }
}
