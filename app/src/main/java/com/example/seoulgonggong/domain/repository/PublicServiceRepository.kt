package com.example.seoulgonggong.domain.repository

import com.example.seoulgonggong.domain.model.PublicServices

interface PublicServiceRepository{
    suspend fun getData():Result<PublicServices>
}