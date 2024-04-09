package com.example.seoulgonggong.data.repository

import com.example.seoulgonggong.data.service.PublicServiceService
import com.example.seoulgonggong.domain.model.PublicServices
import com.example.seoulgonggong.domain.repository.PublicServiceRepository
import javax.inject.Inject

class DefaultPublicServiceRepository @Inject constructor(private val publicServiceService: PublicServiceService):PublicServiceRepository {
    override suspend fun getData(): Result<PublicServices> {
       //todo 로직 작성

    }
}