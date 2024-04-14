package com.example.seoulgonggong.ui.facility.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.seoulgonggong.ui.uimodel.UiSportsFacility
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SportsFacilityDetailViewModel @Inject constructor() : ViewModel() {

    private val _facility: MutableLiveData<UiSportsFacility> = MutableLiveData()
    val facility: LiveData<UiSportsFacility> = _facility

    private val _openNaverMap: MutableLiveData<String> = MutableLiveData()
    val openNaverMap: LiveData<String> = _openNaverMap

    fun setFacilityData(facility: UiSportsFacility) {
        _facility.value = facility
    }

    fun openNaverMap() {
        _openNaverMap.value = facility.value?.address
    }
}
