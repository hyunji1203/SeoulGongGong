package com.example.seoulgonggong.ui.facility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seoulgonggong.domain.repository.SportsFacilityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsFacilityViewModel @Inject constructor(
    private val repository: SportsFacilityRepository,
) : ViewModel() {

    private val _listOpenEvent: MutableLiveData<Boolean> = MutableLiveData(false)
    val listOpenEvent: LiveData<Boolean> = _listOpenEvent

    fun getData() {
        viewModelScope.launch {
            repository.getSportsFacility().onSuccess {
                // 데이터 활용하기
            }
        }
    }

    fun openList() {
        _listOpenEvent.value = !(_listOpenEvent.value ?: false)
    }
}
