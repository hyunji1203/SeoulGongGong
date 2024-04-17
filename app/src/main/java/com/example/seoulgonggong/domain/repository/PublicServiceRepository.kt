package com.example.seoulgonggong.domain.repository

import com.example.seoulgonggong.domain.model.SportsServices

interface PublicServiceRepository{
    suspend fun getServices():Result<SportsServices>
}