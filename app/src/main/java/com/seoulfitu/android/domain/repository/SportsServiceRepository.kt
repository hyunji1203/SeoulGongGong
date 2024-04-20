package com.seoulfitu.android.domain.repository

import com.seoulfitu.android.domain.model.SportsServices


interface SportsServiceRepository{
    suspend fun getServices():Result<SportsServices>
}