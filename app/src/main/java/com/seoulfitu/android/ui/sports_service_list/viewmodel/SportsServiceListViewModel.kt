package com.seoulfitu.android.ui.sports_service_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.android.domain.repository.SportsServiceRepository
import com.seoulfitu.android.ui.sports_service_list.uistate.SportsServiceListUiState
import com.seoulfitu.android.ui.uimodel.UiSportsService
import com.seoulfitu.android.ui.uimodel.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsServiceListViewModel @Inject constructor(private val sportsServiceRepository: SportsServiceRepository) :
    ViewModel() {
    private var services: List<UiSportsService> = listOf()
    private val _uiState: MutableLiveData<SportsServiceListUiState> = MutableLiveData()
    val uiState: LiveData<SportsServiceListUiState> = _uiState
    fun getServices() {
        viewModelScope.launch {
            val result = sportsServiceRepository.getServices()
            result.onSuccess {
                services = it.toUi()
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
        val results = services.filter { it.title.contains(text) }
        _uiState.value = SportsServiceListUiState(
            isSuccess = true, result = results
        )
    }
}