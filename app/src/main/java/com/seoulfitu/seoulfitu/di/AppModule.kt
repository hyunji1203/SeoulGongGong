package com.seoulfitu.seoulfitu.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.seoulfitu.seoulfitu.BuildConfig
import com.seoulfitu.seoulfitu.data.service.GeocoderService
import com.seoulfitu.seoulfitu.data.service.ParticulateMatterService
import com.seoulfitu.seoulfitu.data.service.SportsFacilityService
import com.seoulfitu.seoulfitu.data.service.SportsServiceService
import com.seoulfitu.seoulfitu.data.service.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    private val json = Json { ignoreUnknownKeys = true }
    private val okHttpClient =
        OkHttpClient.Builder().connectTimeout(200, TimeUnit.SECONDS)
            .readTimeout(200, TimeUnit.SECONDS)
            .writeTimeout(200, TimeUnit.SECONDS)
            .build()

    private const val HEADER_NAVER_GEOCODING_CLIENT_ID = "X-NCP-APIGW-API-KEY-ID"
    private const val HEADER_NAVER_GEOCODING_CLIENT_SECRET = "X-NCP-APIGW-API-KEY"

    @Provides
    @Singleton
    @NaverGeocodeClient
    fun provideNaverGeocodeClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(200, TimeUnit.SECONDS)
            .readTimeout(200, TimeUnit.SECONDS)
            .writeTimeout(200, TimeUnit.SECONDS)
            .addInterceptor(
                Interceptor { chain ->
                    val request =
                        chain.request().newBuilder()
                            .addHeader(HEADER_NAVER_GEOCODING_CLIENT_ID, BuildConfig.NAVER_GEOCODE_CLIENT_ID)
                            .addHeader(HEADER_NAVER_GEOCODING_CLIENT_SECRET, BuildConfig.NAVER_GEOCODE_CLIENT_SECRET).build()
                    chain.proceed(request)
                },
            )
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                },
            ).build()
    }

    @Provides
    @Singleton
    @SeoulOpenApiRetrofit
    fun provideSeoulOpenApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SEOUL_OPEN_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()
    }

    @Provides
    @Singleton
    @WeatherRetrofit
    fun provideWeatherRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.OPEN_DATA_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()

    @Provides
    @Singleton
    @GeocoderRetrofit
    fun provideGeocoderRetrofit(
        @NaverGeocodeClient client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.GEOCODER_API_BASE_URL).client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaTypeOrNull()!!)).build()
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
    fun provideGeocoderService(
        @GeocoderRetrofit retrofit: Retrofit,
    ): GeocoderService {
        return retrofit.create(GeocoderService::class.java)
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

    @Provides
    @Singleton
    fun provideSportsServiceService(@SeoulOpenApiRetrofit retrofit: Retrofit): SportsServiceService {
        return retrofit.create(SportsServiceService::class.java)
    }
}
