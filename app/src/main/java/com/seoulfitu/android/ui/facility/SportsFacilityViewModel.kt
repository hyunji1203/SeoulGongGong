package com.seoulfitu.android.ui.facility

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.android.domain.repository.GeocodingRepository
import com.seoulfitu.android.domain.repository.SportsFacilityRepository
import com.seoulfitu.android.ui.uimodel.UiSportsFacility
import com.seoulfitu.android.ui.uimodel.UiSportsFacilityList
import com.seoulfitu.android.ui.uimodel.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsFacilityViewModel @Inject constructor(
    private val facilityRepository: SportsFacilityRepository,
    private val geocodingRepository: GeocodingRepository,
) : ViewModel() {

    private val _sportsFacilities: MutableLiveData<List<UiSportsFacility>> = MutableLiveData()
    val sportsFacilities: LiveData<List<UiSportsFacility>> = _sportsFacilities

    val searchWord: MutableLiveData<String> = MutableLiveData("")
    private val _listSportsFacilities: MutableLiveData<UiSportsFacilityList> = MutableLiveData()
    val listSportsFacilities: LiveData<UiSportsFacilityList> = _listSportsFacilities

    private val _listOpenEvent: MutableLiveData<Boolean> = MutableLiveData()
    val listOpenEvent: LiveData<Boolean> = _listOpenEvent

    private val _detailOpenEvent: MutableLiveData<UiSportsFacility> = MutableLiveData()
    val detailOpenEvent: LiveData<UiSportsFacility> = _detailOpenEvent

    private val _test: MutableLiveData<Boolean> = MutableLiveData()
    val test: LiveData<Boolean> = _test

    fun getAllFacilities() {
        viewModelScope.launch {
            facilityRepository.getSportsFacility().onSuccess { facilities ->
                _sportsFacilities.value = facilities.map { it.toUi() }

                searchPosition()

                _listSportsFacilities.value =
                    UiSportsFacilityList(_sportsFacilities.value ?: emptyList())

                _test.value = true
            }
        }
    }

    private suspend fun searchPosition() {
        _sportsFacilities.value?.forEach { facility ->
            geocodingRepository.geocode(facility.address).onSuccess {
                if (it.values.isNotEmpty()) {
                    val cor = it.values.first().coordinate
                    facility.x = cor.x
                    facility.y = cor.y
                }
            }
            Log.d(
                "checkCoordinate",
                "${facility.facilityName} : ${facility.x} , ${facility.y}"
            )
        }
    }

    fun searchFacility() {
        _listSportsFacilities.value =
            UiSportsFacilityList(_sportsFacilities.value?.filter {
                it.facilityName.contains(
                    searchWord.value.toString()
                )
            } ?: emptyList())
        _listOpenEvent.value = true
    }

    fun openFacilityList() {
        _listOpenEvent.value = true
        _listSportsFacilities.value = UiSportsFacilityList(_sportsFacilities.value ?: emptyList())
    }

    fun openFacilityDetail(item: UiSportsFacility) {
        _detailOpenEvent.value = item
    }
}
