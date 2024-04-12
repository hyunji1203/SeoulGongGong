package com.example.seoulgonggong.di

import com.example.seoulgonggong.BuildConfig
import com.example.seoulgonggong.data.service.SportsFacilityService
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


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    private val json = Json { ignoreUnknownKeys = true }

    private const val HEADER_NAVER_GEOCODING_CLIENT_ID = "X-NCP-APIGW-API-KEY-ID"
    private const val HEADER_NAVER_GEOCODING_CLIENT_SECRET = "X-NCP-APIGW-API-KEY"

    @Provides
    @Singleton
    @NaverMapClient
    fun provideNaverMapClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain
                    .request()
                    .newBuilder()
                    .addHeader(HEADER_NAVER_GEOCODING_CLIENT_ID, BuildConfig.NAVER_MAP_CLIENT_ID)
                    .addHeader(HEADER_NAVER_GEOCODING_CLIENT_SECRET, BuildConfig.NAVER_MAP_CLIENT_SECRET)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    @SeoulOpenApiRetrofit
    fun provideSeoulOpenApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SEOUL_OPEN_API_BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
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

    @Provides
    @Singleton
    @GeocodingRetrofit
    fun provideGeocodingRetrofit(@NaverMapClient client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.GEOCODING_API_BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()
    }

    @Provides
    @Singleton
    @ReverseGeocodingRetrofit
    fun provideReverseGeocodingRetrofit(@NaverMapClient client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.REVERSE_GEOCODING_API_BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()
    }


    @Provides
    @Singleton
    fun provideService(@SeoulOpenApiRetrofit retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }

    @Provides
    @Singleton
    fun provideSportsFacilityService(@SeoulOpenApiRetrofit retrofit: Retrofit): SportsFacilityService {
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
