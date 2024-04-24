package com.seoulfitu.android.ui.facility.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.android.domain.model.SportsFacilityInfo
import com.seoulfitu.android.domain.repository.FacilityScrapRepository
import com.seoulfitu.android.ui.uimodel.UiSportsFacility
import com.seoulfitu.android.ui.uimodel.mapper.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsFacilityDetailViewModel @Inject constructor(
    private val facilityScrapRepository: FacilityScrapRepository,
) : ViewModel() {
    private val _facility: MutableLiveData<UiSportsFacility> = MutableLiveData()
    val facility: LiveData<UiSportsFacility> = _facility

    private val _openNaverMap: MutableLiveData<String> = MutableLiveData()
    val openNaverMap: LiveData<String> = _openNaverMap

    private val _openWebPage: MutableLiveData<String> = MutableLiveData()
    val openWebPage: LiveData<String> = _openWebPage

    private val _openPhoneDial: MutableLiveData<String> = MutableLiveData()
    val openPhoneDial: LiveData<String> = _openPhoneDial

    private val _scrapStatus: MutableLiveData<Boolean> = MutableLiveData()
    val scrapStatue: LiveData<Boolean> = _scrapStatus

    fun setFacilityData(facility: UiSportsFacility) {
        _facility.value = facility
        _scrapStatus.value = facility.isScrap
    }

    fun openNaverMap() {
        _openNaverMap.value = facility.value?.address
    }

    fun openWebPage() {
        _openWebPage.value = facility.value?.homepageUrl
    }

    fun openPhoneDial() {
        _openPhoneDial.value =
            SportsFacilityInfo.getPhoneNumber(facility.value?.phoneNumber ?: "")
    }

    fun scrapFacility() {
        viewModelScope.launch(Dispatchers.IO) {
            if (facility.value?.isScrap == true) {
                facilityScrapRepository.deleteScrap(facility.value!!.toDomain(true))
                _facility.value?.isScrap = false
                _scrapStatus.postValue(false)
            } else {
                facilityScrapRepository.scrap(facility.value!!.toDomain(false))
                _facility.value?.isScrap = true
                _scrapStatus.postValue(true)
            }
        }
    }
}
