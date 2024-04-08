package com.example.seoulgonggong.ui.facility

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seoulgonggong.domain.repository.SportsFacilityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicSportsFacilityViewModel @Inject constructor(
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
