package com.example.seoulgonggong.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seoulgonggong.BuildConfig
import com.example.seoulgonggong.domain.model.BaseDateTime
import com.example.seoulgonggong.domain.model.WeatherStatus
import com.example.seoulgonggong.domain.repository.GeoRepository
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
        private val geoRepository: GeoRepository,
    ) : ViewModel() {
        private val _temperature = MutableLiveData<Int>()
        val temperature: LiveData<Int> = _temperature

        private val _weatherStatus = MutableLiveData<WeatherStatus>()
        val weatherStatus: LiveData<WeatherStatus> = _weatherStatus

        private val _particulateMatter = MutableLiveData<Int>()
        val particulateMatter: LiveData<Int> = _particulateMatter

        private val _particulateMatterStatus = MutableLiveData<String>()
        val particulateMatterStatus: LiveData<String> = _particulateMatterStatus

        private val _observatory = MutableLiveData<String>()
        val observatory: LiveData<String> = _observatory

        private val _throwable = MutableLiveData<String>()
        val throwable: LiveData<String> = _throwable

        fun fetchWeather(
            latitude: Double,
            longitude: Double,
        ) {
            val baseDateTime = BaseDateTime.getBaseDateTime()
            val point = GeoPointConverter().convert(latitude, longitude)
            viewModelScope.launch {
                runCatching {
                    weatherRepository.getWeather(
                        serviceKey = BuildConfig.OPEN_DATA_SERVICE_KEY,
                        baseDate = baseDateTime.baseDate,
                        baseTime = baseDateTime.baseTime,
                        nx = point.nx,
                        ny = point.ny,
                    )
                }.onSuccess { weathers ->
                    _temperature.value = weathers.temperature.toInt()
                    _weatherStatus.value = WeatherStatus.findByName(weathers.getStatus())
                }.onFailure {
                    _throwable.value = it.message
                }
            }
        }

        fun fetchParticulateMatter(
            latitude: Double,
            longitude: Double,
        ) {
            viewModelScope.launch {
                runCatching {
                    val address = geoRepository.getCityAddress(latitude, longitude)
                    particulateMatterRepository.getParticulateMatter(address)
                }.onSuccess { particulateMatterInfo ->
                    _particulateMatter.value = particulateMatterInfo.pm10
                    _particulateMatterStatus.value = particulateMatterInfo.idexNm
                    _observatory.value = particulateMatterInfo.msrsteNm
                }.onFailure {
                    _throwable.value = it.message
                }
            }
        }
    }
