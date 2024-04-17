package com.seoulfitu.android.ui.facility

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.android.domain.repository.SportsFacilityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsFacilityViewModel @Inject constructor(
    private val repository: SportsFacilityRepository,
) : ViewModel() {

    fun getData() {
        viewModelScope.launch {
            repository.getSportsFacility().onSuccess {
                // 데이터 활용하기
            }
        }
    }
}
