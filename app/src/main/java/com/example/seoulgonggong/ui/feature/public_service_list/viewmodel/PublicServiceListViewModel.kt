package com.example.seoulgonggong.ui.feature.public_service_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seoulgonggong.domain.repository.PublicServiceRepository
import com.example.seoulgonggong.ui.feature.public_service_list.uistate.PublicServiceListUiState
import com.example.seoulgonggong.ui.model.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicServiceListViewModel @Inject constructor(private val publicServiceRepository: PublicServiceRepository) :
    ViewModel() {
    private val _uiState: MutableLiveData<PublicServiceListUiState> = MutableLiveData()
    val uiState: LiveData<PublicServiceListUiState> = _uiState
    fun getData() {
        viewModelScope.launch {
            val result = publicServiceRepository.getData()
            result.onSuccess {
                _uiState.value = PublicServiceListUiState(
                    isSuccess = true, result = it.toUi()
                )
            }.onFailure {
                _uiState.value = PublicServiceListUiState(
                    isSuccess = false, errorMessage = it.message
                )
            }
        }
    }
}