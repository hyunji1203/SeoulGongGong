package com.example.seoulgonggong.di

import com.example.seoulgonggong.BuildConfig
import com.example.seoulgonggong.data.service.ParticulateMatterService
import com.example.seoulgonggong.data.service.Service
import com.example.seoulgonggong.data.service.SportsFacilityService
import com.example.seoulgonggong.data.service.WeatherService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val json = Json { ignoreUnknownKeys = true }

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

    @Provides
    @Singleton
    @WeatherRetrofit
    fun provideWeatherRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.OPEN_DATA_API_BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()

    // 샘플 서비스 (이런 식으로 작성하면 된다는 예시)
    @Provides
    @Singleton
    fun provideService(
        @SeoulOpenApiRetrofit retrofit: Retrofit,
    ): Service {
        return retrofit.create(Service::class.java)
    }

    @Provides
    @Singleton
    fun provideSportsFacilityService(
        @SeoulOpenApiRetrofit retrofit: Retrofit,
    ): SportsFacilityService {
        return retrofit.create(SportsFacilityService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherService(
        @WeatherRetrofit retrofit: Retrofit,
    ): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideParticulateMatterService(
        @SeoulOpenApiRetrofit retrofit: Retrofit,
    ): ParticulateMatterService {
        return retrofit.create(ParticulateMatterService::class.java)
    }
}
