package com.seoulfitu.android.ui.sports_service_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.android.domain.model.Coordinate
import com.seoulfitu.android.domain.model.RegionsWithCoordinate
import com.seoulfitu.android.domain.repository.GeocodingRepository
import com.seoulfitu.android.domain.repository.ServiceScrapRepository
import com.seoulfitu.android.domain.repository.SportsServiceRepository
import com.seoulfitu.android.ui.sports_service_list.uistate.SportsServiceListUiState
import com.seoulfitu.android.ui.uimodel.UiSelectedOptions
import com.seoulfitu.android.ui.uimodel.UiSportsService
import com.seoulfitu.android.ui.uimodel.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SportsServiceListViewModel @Inject constructor(
    private val sportsServiceRepository: SportsServiceRepository,
    private val geocodingRepository: GeocodingRepository,
    private val scrapRepository: ServiceScrapRepository,
) : ViewModel() {
    // 전체 서비스
    private var services: List<UiSportsService> = listOf()

    // 검색 결과
    private var searchedServices: List<UiSportsService> = listOf()
    private var searchKeyword: String = ""

    private val _uiState: MutableLiveData<SportsServiceListUiState> =
        MutableLiveData(SportsServiceListUiState(isSuccess = true))
    val uiState: LiveData<SportsServiceListUiState> = _uiState

    fun getServices() {
        viewModelScope.launch {
            val result = sportsServiceRepository.getServices()
            result.onSuccess { sportsService ->
                services = sportsService.services.map { it.toUi() }
                    .map { it.copy(scrapped = isScraped(it)) }
                searchedServices = services
                _uiState.value = _uiState.value?.copy(isSuccess = true, result = services)
                services.forEach { service ->
                    reverseGeocode(service)
                }
            }.onFailure {
                _uiState.value = _uiState.value?.copy(
                    isSuccess = false, errorMessage = it.message
                )
            }
        }
    }

    fun getCurrentService() {
        viewModelScope.launch {
            val currentServices = _uiState.value?.result?.map { it.copy(scrapped = isScraped(it)) }
            _uiState.value = _uiState.value?.copy(isSuccess = true, result = currentServices ?: emptyList())
        }
    }

    private suspend fun reverseGeocode(service: UiSportsService) {
        viewModelScope.launch {
            val result = geocodingRepository.reverseGeocode(
                Coordinate(service.info.xCoordinate, service.info.yCoordinate)
            )
            result.onSuccess {
                service.info.address = formatRegion(it)
            }
        }

    }

    private fun formatRegion(regionsWithCoordinate: RegionsWithCoordinate): String {
        regionsWithCoordinate.values[0].apply {
            return "${area1.name} ${area2.name} ${area3.name} ${area4.name}"
        }
    }

    fun updateSearchKeyword(text: String) {
        searchKeyword = text
    }

    fun searchData() {
        val results = services.filter { it.info.title.contains(searchKeyword) }
        searchedServices = results
        viewModelScope.launch {
            _uiState.value = _uiState.value?.copy(
                isSuccess = true,
                result = searchedServices.map { it.copy(scrapped = isScraped(it)) }
            )
        }
    }

    fun applyOptions(options: UiSelectedOptions) {
        val results = searchedServices.filter { service ->
            val cityFit = if (options.cities.isEmpty()) true else options.cities.any { city ->
                service.info.address.contains(city)
            }
            val serviceFit =
                if (options.services.isEmpty()) true else options.services.any { serviceOption ->
                    service.info.subCategory.contains(serviceOption)
                }
            val priceFit =
                if (options.price.isEmpty()) true else service.info.payment.contains(options.price)
            val serviceStatusFit =
                if (options.serviceStatus.isEmpty()) true else options.serviceStatus.any { status ->
                    service.info.status.replace(" ", "").contains(status.replace(" ", ""))
                }

            cityFit and serviceFit and priceFit and serviceStatusFit /*and priceFit and serviceStatusFit*/
        }
        viewModelScope.launch {
            _uiState.value = _uiState.value?.copy(
                isSuccess = true,
                result = results.map { it.copy(scrapped = isScraped(it)) },
                selectedOptions = options
            )
        }
    }

    private suspend fun isScraped(sportsService: UiSportsService): Boolean {
        return withContext(Dispatchers.IO) {
            val scrapedServices = scrapRepository.getAll()
            scrapedServices.any { it.serviceName == sportsService.info.title }
        }
    }
}
