package com.example.seoulgonggong.domain.repository

import com.example.seoulgonggong.domain.model.SportsServices

interface SportsServiceRepository{
    suspend fun getServices():Result<SportsServices>
}