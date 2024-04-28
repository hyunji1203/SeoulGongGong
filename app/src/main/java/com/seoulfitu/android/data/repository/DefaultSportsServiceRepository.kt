package com.seoulfitu.android.data.repository

import com.seoulfitu.android.data.ERROR_MESSAGE_FAIL_RESULT
import com.seoulfitu.android.data.ERROR_MESSAGE_NO_BODY
import com.seoulfitu.android.data.model.mapper.toDomain
import com.seoulfitu.android.data.service.SportsServiceService
import com.seoulfitu.android.domain.model.SportsServices
import com.seoulfitu.android.domain.repository.SportsServiceRepository
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