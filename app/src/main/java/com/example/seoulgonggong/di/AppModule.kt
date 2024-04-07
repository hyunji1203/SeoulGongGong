package com.example.seoulgonggong.di

import com.example.seoulgonggong.data.ResponseFilterInterceptor
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
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WeatherRetrofit

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    private val baseUrlWeather = "http://apis.data.go.kr/"
    private val json = Json { ignoreUnknownKeys = true }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(ResponseFilterInterceptor())
        }.build()

    @Singleton
    @Provides
    @WeatherRetrofit
    fun provideWeatherRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrlWeather)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()

    @Singleton
    @Provides
    fun provideWeatherService(
        @WeatherRetrofit retrofit: Retrofit,
    ): WeatherService = retrofit.create(WeatherService::class.java)
}
