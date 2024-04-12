package com.example.seoulgonggong.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seoulgonggong.BuildConfig
import com.example.seoulgonggong.domain.model.BaseDateTime
import com.example.seoulgonggong.domain.repository.GeoRepository
import com.example.seoulgonggong.domain.repository.ParticulateMatterRepository
import com.example.seoulgonggong.domain.repository.WeatherRepository
import com.example.seoulgonggong.ui.uimodel.UiParticulateMatter
import com.example.seoulgonggong.ui.uimodel.UiWeather
import com.example.seoulgonggong.ui.uimodel.mapper.toUi
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
        private val _weatherInfo = MutableLiveData<UiWeather>()
        val weatherInfo: LiveData<UiWeather> = _weatherInfo

        private val _particulateMatterInfo = MutableLiveData<UiParticulateMatter>()
        val particulateMatterInfo: LiveData<UiParticulateMatter> = _particulateMatterInfo

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
                    _weatherInfo.value = weathers.toUi()
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
                    _particulateMatterInfo.value = particulateMatterInfo.toUi()
                }.onFailure {
                    _throwable.value = it.message
                }
            }
        }
    }
