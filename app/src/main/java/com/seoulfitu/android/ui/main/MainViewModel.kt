package com.seoulfitu.android.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.android.domain.model.BaseDateTime
import com.seoulfitu.android.domain.repository.FacilityScrapRepository
import com.seoulfitu.android.domain.repository.GeoRepository
import com.seoulfitu.android.domain.repository.ParticulateMatterRepository
import com.seoulfitu.android.domain.repository.WeatherRepository
import com.seoulfitu.android.ui.uimodel.UiParticulateMatter
import com.seoulfitu.android.ui.uimodel.UiSportsFacility
import com.seoulfitu.android.ui.uimodel.UiWeather
import com.seoulfitu.android.ui.uimodel.mapper.toUi
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
        private val facilityScrapRepository: FacilityScrapRepository,
    ) : ViewModel() {
        private val _weatherInfo = MutableLiveData<UiWeather>()
        val weatherInfo: LiveData<UiWeather> = _weatherInfo

        private val _particulateMatterInfo = MutableLiveData<UiParticulateMatter>()
        val particulateMatterInfo: LiveData<UiParticulateMatter> = _particulateMatterInfo

        private val _scrapedFacilities = MutableLiveData<List<UiSportsFacility>>()
        val scrapedFacilities: LiveData<List<UiSportsFacility>> = _scrapedFacilities

        private val _throwable = MutableLiveData<String>()
        val throwable: LiveData<String> = _throwable

        fun fetchWeather(
            latitude: Double,
            longitude: Double,
        ) {
            viewModelScope.launch {
                runCatching {
                    weatherRepository.getWeather(
                        baseDateTime = BaseDateTime.getBaseDateTime(),
                        point = GeoPointConverter().convert(latitude, longitude),
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

        fun fetchSportsFacilityScrap() {
            viewModelScope.launch {
                runCatching {
                    facilityScrapRepository.getAll()
                }.onSuccess { scrapedFacilities ->
                    _scrapedFacilities.value = scrapedFacilities.map { it.toUi() }
                }.onFailure {
                    _throwable.value = it.message
                }
            }
        }
    }
