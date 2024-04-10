package com.example.seoulgonggong.di

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.example.seoulgonggong.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    private const val HEADER_NAVER_GEOCODING_CLIENT_ID = "X-NCP-APIGW-API-KEY-ID"
    private const val HEADER_NAVER_GEOCODING_CLIENT_SECRET = "X-NCP-APIGW-API-KEY"

    @Provides
    @Singleton
    @GeocodingClient
    fun provideGeocodingClient(): OkHttpClient {
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
            .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
            .build()
    }

    @Provides
    @Singleton
    @GeocodingRetrofit
    fun provideGeocodingRetrofit(@GeocodingClient client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.Geocoding_API_BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
            .build()
    }
}