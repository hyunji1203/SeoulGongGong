package com.example.seoulgonggong.ui.facility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seoulgonggong.domain.repository.SportsFacilityRepository
import com.example.seoulgonggong.ui.uimodel.UiSportsFacility
import com.example.seoulgonggong.ui.uimodel.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsFacilityViewModel @Inject constructor(
    private val repository: SportsFacilityRepository,
) : ViewModel() {

    private val _sportsFacilities: MutableLiveData<List<UiSportsFacility>> = MutableLiveData()
    val sportsFacilities: LiveData<List<UiSportsFacility>> = _sportsFacilities

    private val _listOpenEvent: MutableLiveData<Boolean> = MutableLiveData()
    val listOpenEvent: LiveData<Boolean> = _listOpenEvent

    private val _detailOpenEvent: MutableLiveData<UiSportsFacility> = MutableLiveData()
    val detailOpenEvent: LiveData<UiSportsFacility> = _detailOpenEvent

    fun getData() {
        viewModelScope.launch {
            repository.getSportsFacility().onSuccess { facilities ->
                val uiFacilities = mutableListOf<UiSportsFacility>()
                facilities.forEach {
                    if (!it.info.type.contains(EXCLUDE_WORD)) uiFacilities.add(it.toUi())
                }
                _sportsFacilities.value = uiFacilities.toList()
            }
        }
    }

    fun openList() {
        _listOpenEvent.value = true
    }

    fun openFacilityDetail(item: UiSportsFacility) {
        _detailOpenEvent.value = item
    }

    companion object {
        private const val EXCLUDE_WORD = "학교"
    }
}
