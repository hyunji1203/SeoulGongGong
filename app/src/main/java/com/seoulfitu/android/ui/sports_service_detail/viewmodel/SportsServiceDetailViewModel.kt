package com.seoulfitu.android.ui.sports_service_detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.android.domain.model.Coordinate
import com.seoulfitu.android.domain.model.RegionWithCoordinate
import com.seoulfitu.android.domain.repository.GeocodingRepository
import com.seoulfitu.android.ui.sports_service_detail.uistate.SportsServiceDetailUiState
import com.seoulfitu.android.ui.uimodel.UiSportsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class SportsServiceDetailViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) : ViewModel() {
    private val _sportService: MutableLiveData<SportsServiceDetailUiState> =
        MutableLiveData(SportsServiceDetailUiState())
    val sportsService: MutableLiveData<SportsServiceDetailUiState> = _sportService

    fun setSportsService(service: UiSportsService) {
        viewModelScope.launch {
            val result = geocodingRepository.reverseGeocode(
                Coordinate(service.xCoordinate, service.yCoordinate)
            )
            result.onSuccess {
                val regionWithCoordinate = it.values[0]
                _sportService.value = _sportService.value?.copy(
                    isSuccess = true, result = service.copy(
                        registrationStartDate = formatRegistrationDate(service.registrationStartDate),
                        registrationEndDate = formatRegistrationDate(service.registrationEndDate),
                        address = formatRegion(regionWithCoordinate)
                    )
                )
            }.onFailure {
                _sportService.value = _sportService.value?.copy(
                    errorMessage = it.message.toString()
                )
            }
        }
    }

    private fun formatRegion(regionWithCoordinate: RegionWithCoordinate): String {
        return "${regionWithCoordinate.area1.name} ${regionWithCoordinate.area2.name} ${regionWithCoordinate.area3.name} ${regionWithCoordinate.area4.name}"
    }

    private fun formatRegistrationDate(date: String): String {
        // 자릿수 통일을 위해 밀리초 제거
        val dateWithoutMills = date.split(".")[0]
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.parse(dateWithoutMills, dateTimeFormatter)
        val stringFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
        return dateTime.format(stringFormatter)
    }
}