package com.seoulfitu.android.ui.sports_service_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.android.domain.repository.ServiceScrapRepository
import com.seoulfitu.android.ui.uimodel.UiSportsService
import com.seoulfitu.android.ui.uimodel.mapper.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class SportsServiceDetailViewModel @Inject constructor(
    private val scrapRepository: ServiceScrapRepository,
) : ViewModel() {
    private val _service: MutableLiveData<UiSportsService> = MutableLiveData(UiSportsService())
    val service: LiveData<UiSportsService> = _service

    private lateinit var originalServiceInfo: UiSportsService

    fun setSportsService(service: UiSportsService) {
        _service.value = service.copy(
            info = service.info.copy(
                registrationStartDate = formatRegistrationDate(service.info.registrationStartDate),
                registrationEndDate = formatRegistrationDate(service.info.registrationEndDate)
            )
        )
        originalServiceInfo = service
    }

    fun scrapService() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = service.value ?: return@launch
            if (result.scrapped) {
                scrapRepository.deleteScrap(originalServiceInfo.toDomain())
            } else {
                scrapRepository.scrap(originalServiceInfo.toDomain())
            }
            _service.postValue(
                _service.value?.copy(scrapped = !result.scrapped)
            )
        }
    }

    private fun formatRegistrationDate(date: String): String {
        // 자릿수 통일을 위해 밀리초 제거
        val dateWithoutMills = date.split(".")[0]
        val dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PARSING_PATTERN)
        val dateTime = LocalDateTime.parse(dateWithoutMills, dateTimeFormatter)
        val stringFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN)
        return dateTime.format(stringFormatter)
    }

    companion object {
        private const val DATE_TIME_PARSING_PATTERN = "yyyy-MM-dd HH:mm:ss"
        private const val DATE_TIME_FORMAT_PATTERN = "yyyy.MM.dd HH:mm"
    }
}