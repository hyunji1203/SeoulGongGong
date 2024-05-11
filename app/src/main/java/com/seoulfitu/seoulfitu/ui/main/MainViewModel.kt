package com.seoulfitu.seoulfitu.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.seoulfitu.domain.model.BaseDateTime
import com.seoulfitu.seoulfitu.domain.model.Coordinate
import com.seoulfitu.seoulfitu.domain.model.RegionsWithCoordinate
import com.seoulfitu.seoulfitu.domain.model.Town.Companion.findTownName
import com.seoulfitu.seoulfitu.domain.repository.FacilityScrapRepository
import com.seoulfitu.seoulfitu.domain.repository.GeocodingRepository
import com.seoulfitu.seoulfitu.domain.repository.ParticulateMatterRepository
import com.seoulfitu.seoulfitu.domain.repository.ServiceScrapRepository
import com.seoulfitu.seoulfitu.domain.repository.WeatherRepository
import com.seoulfitu.seoulfitu.ui.uimodel.UiParticulateMatter
import com.seoulfitu.seoulfitu.ui.uimodel.UiSportsFacility
import com.seoulfitu.seoulfitu.ui.uimodel.UiSportsService
import com.seoulfitu.seoulfitu.ui.uimodel.UiWeather
import com.seoulfitu.seoulfitu.ui.uimodel.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val particulateMatterRepository: ParticulateMatterRepository,
    private val geocodingRepository: GeocodingRepository,
    private val facilityScrapRepository: FacilityScrapRepository,
    private val serviceScrapRepository: ServiceScrapRepository,
) : ViewModel() {
    private val _weatherInfo = MutableLiveData(UiWeather())
    val weatherInfo: LiveData<UiWeather> = _weatherInfo

    private val _particulateMatterInfo = MutableLiveData<UiParticulateMatter>()
    val particulateMatterInfo: LiveData<UiParticulateMatter> = _particulateMatterInfo

    private val _scrapedFacilities = MutableLiveData<List<UiSportsFacility>>()
    val scrapedFacilities: LiveData<List<UiSportsFacility>> = _scrapedFacilities

    private val _scrapedServices = MutableLiveData<List<UiSportsService>>()
    val scrapedServices: LiveData<List<UiSportsService>> = _scrapedServices

    private val _facilityDetailOpenEvent: MutableLiveData<UiSportsFacility> = MutableLiveData()
    val facilityDetailOpenEvent: LiveData<UiSportsFacility> = _facilityDetailOpenEvent

    private val _serviceDetailOpenEvent: MutableLiveData<UiSportsService> = MutableLiveData()
    val serviceDetailOpenEvent: LiveData<UiSportsService> = _serviceDetailOpenEvent

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
                val address = reverseGeocode(latitude, longitude)
                particulateMatterRepository.getParticulateMatter(findTownName(address))
            }.onSuccess { particulateMatterInfo ->
                _particulateMatterInfo.value = particulateMatterInfo.toUi()
            }.onFailure {
                _throwable.value = it.message
            }
        }
    }

    private suspend fun reverseGeocode(latitude: Double, longitude: Double): String {
        return withContext(Dispatchers.IO) {
            val result = geocodingRepository.reverseGeocode(Coordinate(longitude, latitude))
            result.onSuccess {
                return@withContext formatRegion(it)
            }
            return@withContext ADDRESS_MISSING_VALUE
        }
    }

    private fun formatRegion(regionsWithCoordinate: RegionsWithCoordinate): String {
        regionsWithCoordinate.values[0].apply {
            return "${area1.name} ${area2.name} ${area3.name} ${area4.name}"
        }
    }

    fun fetchSportsFacilityScrap() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                facilityScrapRepository.getAll()
            }.onSuccess { facilities ->
                _scrapedFacilities.postValue(facilities.map { it.toUi(true) })
            }.onFailure {
                _throwable.postValue(it.message)
            }
        }
    }

    fun fetchSportsServiceScrap() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                serviceScrapRepository.getAll()
            }.onSuccess { services ->
                _scrapedServices.postValue(services.map { it.toUi() }.map { it.copy(scrapped = true) })
            }.onFailure {
                _throwable.postValue(it.message)
            }
        }
    }

    fun openFacilityDetail(item: UiSportsFacility) {
        _facilityDetailOpenEvent.value = item
    }

    fun openServiceDetail(item: UiSportsService) {
        _serviceDetailOpenEvent.value = item
    }

    companion object {
        private const val ADDRESS_MISSING_VALUE = "Unknown Address"
    }
}
