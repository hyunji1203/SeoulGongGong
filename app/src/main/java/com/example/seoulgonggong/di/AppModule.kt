package com.example.seoulgonggong.di

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.example.seoulgonggong.BuildConfig
import com.example.seoulgonggong.data.service.PublicSportsFacilityService
import com.example.seoulgonggong.data.service.Service
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    @SeoulOpenApiRetrofit
    fun provideSeoulOpenApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SEOUL_OPEN_API_BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()
    }

    // 샘플 서비스 (이런 식으로 작성하면 된다는 예시)
    @Provides
    @Singleton
    fun provideService(@SeoulOpenApiRetrofit retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }

    @Provides
    @Singleton
    fun provideSportsFacilityService(@SeoulOpenApiRetrofit retrofit: Retrofit): PublicSportsFacilityService {
        return retrofit.create(PublicSportsFacilityService::class.java)
    }
}
