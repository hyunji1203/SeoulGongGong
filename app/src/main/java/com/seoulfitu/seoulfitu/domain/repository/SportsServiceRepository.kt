package com.seoulfitu.seoulfitu.domain.repository

import com.seoulfitu.seoulfitu.domain.model.SportsServices


interface SportsServiceRepository{
    suspend fun getServices():Result<SportsServices>
}
