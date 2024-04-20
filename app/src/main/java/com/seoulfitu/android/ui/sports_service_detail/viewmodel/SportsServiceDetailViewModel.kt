package com.seoulfitu.android.ui.sports_service_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.android.domain.model.Coordinate
import com.seoulfitu.android.domain.repository.GeocodingRepository
import com.seoulfitu.android.ui.uimodel.UiSportsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsServiceDetailViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) : ViewModel() {
    private val _sportService: MutableLiveData<UiSportsService> = MutableLiveData()
    val sportsService: LiveData<UiSportsService> = _sportService

    fun setSportsService(service: UiSportsService) {
        _sportService.value = service
    }

    fun reverseGeocode() {
        viewModelScope.launch {
            val result = geocodingRepository.reverseGeocode(
                Coordinate(_sportService.value?.xCoordinate ?: 0.0, _sportService.value?.yCoordinate ?: 0.0)
            )
            result.onSuccess {
                val areas = it.values[0]
                val address = "${areas.area1.name} ${areas.area2.name} ${areas.area3.name} ${areas.area4.name}"
                _sportService.value = _sportService.value?.copy(address = address)
            }.onFailure {

            }
        }
    }
}