package com.seoulfitu.android.ui.sports_service_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.android.domain.model.SportsService
import com.seoulfitu.android.domain.repository.ServiceScrapRepository
import com.seoulfitu.android.domain.repository.SportsServiceRepository
import com.seoulfitu.android.ui.sports_service_list.uistate.SportsServiceListUiState
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
    private val scrapRepository: ServiceScrapRepository,
) :
    ViewModel() {
    private var services: List<UiSportsService> = listOf()
    private val _uiState: MutableLiveData<SportsServiceListUiState> = MutableLiveData()
    val uiState: LiveData<SportsServiceListUiState> = _uiState
    fun getServices() {
        viewModelScope.launch {
            val result = sportsServiceRepository.getServices()
            result.onSuccess { service ->
                services = service.services.map { it.toUi(isScraped(it)) }
                _uiState.value = SportsServiceListUiState(
                    isSuccess = true, result = services
                )
            }.onFailure {
                _uiState.value = SportsServiceListUiState(
                    isSuccess = false, errorMessage = it.message
                )
            }
        }
    }

    fun searchData(text: String) {
        val results = services.filter { it.info.title.contains(text) }
        _uiState.value = SportsServiceListUiState(
            isSuccess = true, result = results
        )
    }

    private suspend fun isScraped(sportsService: SportsService): Boolean {
        return withContext(Dispatchers.IO) {
            val scrapedServices = scrapRepository.getAll()
            scrapedServices.any { it.serviceName == sportsService.serviceName }
        }
    }
}