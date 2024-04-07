package com.example.seoulgonggong.ui.main

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seoulgonggong.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val weatherRepository: WeatherRepository,
    ) : ViewModel() {
        private val _temp = MutableLiveData<String>()
        val temp: LiveData<String> = _temp

        fun fetchWeather() {
            viewModelScope.launch {
                kotlin.runCatching {
                    weatherRepository.getWeather(
                        serviceKey = "N4E7Om1Q+ZfsXn5KbolmQMkKGMtW6NKltMv/tBhClqRm/U6UMiSeIamXBI9aD6GNs/GQRR6Maxah3UfAcw16+Q==",
                        baseDate = "20240407",
                        baseTime = "0500",
                        nx = 55,
                        ny = 127,
                    )
                }.onSuccess { forecasts ->
                    Log.d("test", forecasts[0].category)
                    _temp.value = forecasts[0].category
                }.onFailure {
                    Log.d("test", "${it.message}")
                    throw NetworkErrorException("네트워크 오류에용")
                }
            }
        }
    }
