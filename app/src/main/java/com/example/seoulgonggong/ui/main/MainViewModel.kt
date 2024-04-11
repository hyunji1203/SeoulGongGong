package com.example.seoulgonggong.ui.main

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seoulgonggong.domain.model.BaseDateTime
import com.example.seoulgonggong.domain.repository.ParticulateMatterRepository
import com.example.seoulgonggong.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val weatherRepository: WeatherRepository,
        private val particulateMatterRepository: ParticulateMatterRepository,
    ) : ViewModel() {
        private val _temperature = MutableLiveData<Int>()
        val temperature: LiveData<Int> = _temperature

        private val _weatherStatus = MutableLiveData<String>()
        val weatherStatus: LiveData<String> = _weatherStatus

        private val _particulateMatter = MutableLiveData<Int>()
        val particulateMatter: LiveData<Int> = _particulateMatter

        private val _particulateMatterStatus = MutableLiveData<String>()
        val particulateMatterStatus: LiveData<String> = _particulateMatterStatus

        private val _observatory = MutableLiveData<String>()
        val observatory: LiveData<String> = _observatory

        fun fetchWeather(
            latitude: Double,
            longitude: Double,
        ) {
            val baseDateTime = BaseDateTime.getBaseDateTime()
            val point = GeoPointConverter().convert(latitude, longitude)
            viewModelScope.launch {
                runCatching {
                    weatherRepository.getWeather(
                        serviceKey = "N4E7Om1Q+ZfsXn5KbolmQMkKGMtW6NKltMv/tBhClqRm/U6UMiSeIamXBI9aD6GNs/GQRR6Maxah3UfAcw16+Q==",
                        baseDate = baseDateTime.baseDate,
                        baseTime = baseDateTime.baseTime,
                        nx = point.nx,
                        ny = point.ny,
                    )
                }.onSuccess { weathers ->
                    Log.d("test", weathers.getStatus())
                    _temperature.value = weathers.temperature.toInt()
                    _weatherStatus.value = weathers.getStatus()
                }.onFailure {
                    Log.d("test", "${it.message}")
                    throw NetworkErrorException("네트워크 오류에용")
                }
            }
        }

        fun fetchParticulateMatter(town: String) {
            viewModelScope.launch {
                runCatching {
                    particulateMatterRepository.getDust(msrsteNm = town)
                }.onSuccess { particulateMatterInfo ->
                    _particulateMatter.value = particulateMatterInfo.pm10
                    _particulateMatterStatus.value = particulateMatterInfo.idexNm
                    _observatory.value = particulateMatterInfo.msrsteNm
                }.onFailure {
                    Log.d("test", "${it.message}")
                    throw NetworkErrorException("네트워크 오류에용")
                }
            }
        }
    }
