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
import javax.inject.Inject

@HiltViewModel
class SportsServiceDetailViewModel @Inject constructor(
    private val scrapRepository: ServiceScrapRepository,
) : ViewModel() {
    private val _service: MutableLiveData<UiSportsService> = MutableLiveData(UiSportsService())
    val service: LiveData<UiSportsService> = _service

    fun setSportsService(service: UiSportsService) {
        _service.value = service.copy(
            info = service.info
        )
    }

    fun scrapService() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = service.value ?: return@launch
            if (result.scrapped) {
                scrapRepository.deleteScrap(result.toDomain())
            } else {
                scrapRepository.scrap(result.toDomain())
            }
            _service.postValue(
                _service.value?.copy(scrapped = !result.scrapped)
            )
        }
    }
}