package com.seoulfitu.android.ui.facility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.android.domain.model.SportsFacility
import com.seoulfitu.android.domain.repository.GeocodingRepository
import com.seoulfitu.android.domain.model.SportsFacility
import com.seoulfitu.android.domain.repository.FacilityScrapRepository
import com.seoulfitu.android.domain.repository.SportsFacilityRepository
import com.seoulfitu.android.ui.uimodel.UiSportsFacility
import com.seoulfitu.android.ui.uimodel.UiSportsFacilityList
import com.seoulfitu.android.ui.uimodel.UiSportsFacilityWithCoordinate
import com.seoulfitu.android.ui.uimodel.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SportsFacilityViewModel
@Inject
constructor(
    private val repository: SportsFacilityRepository,
    private val scrapRepository: FacilityScrapRepository,
    private val geocodingRepository: GeocodingRepository,
) : ViewModel() {
    private val _sportsFacilities: MutableLiveData<List<UiSportsFacility>> = MutableLiveData()
    val sportsFacilities: LiveData<List<UiSportsFacility>> = _sportsFacilities

    private val _facilityWithCoordinate: MutableLiveData<UiSportsFacilityWithCoordinate?> =
        MutableLiveData()
    val facilityWithCoordinate: LiveData<UiSportsFacilityWithCoordinate?> =
        _facilityWithCoordinate

    val searchWord: MutableLiveData<String> = MutableLiveData("")
    private val _listSportsFacilities: MutableLiveData<UiSportsFacilityList> = MutableLiveData()
    val listSportsFacilities: LiveData<UiSportsFacilityList> = _listSportsFacilities

    private val _listOpenEvent: MutableLiveData<Boolean> = MutableLiveData()
    val listOpenEvent: LiveData<Boolean> = _listOpenEvent

    private val _detailOpenEvent: MutableLiveData<UiSportsFacility> = MutableLiveData()
    val detailOpenEvent: LiveData<UiSportsFacility> = _detailOpenEvent

    fun getAllFacilities() {
        viewModelScope.launch {
            repository.getSportsFacility().onSuccess { facilities ->
                _sportsFacilities.value =
                    facilities.map { facility ->
                        val isFacilityScraped = isScraped(facility)
                        facility.toUi(isFacilityScraped)
                    }
                _listSportsFacilities.value =
                    UiSportsFacilityList(_sportsFacilities.value ?: emptyList())

                facilities.forEach { searchPosition(it) }
                _facilityWithCoordinate.value = null
            }
        }
    }

    private suspend fun isScraped(sportsFacility: SportsFacility): Boolean {
        return withContext(Dispatchers.IO) {
            val scrapedFacilities = scrapRepository.getAll()
            scrapedFacilities.any { it.info.facilityName == sportsFacility.info.facilityName }
        }
    }

    private suspend fun searchPosition(sportsFacility: SportsFacility) {
        viewModelScope.launch {
            geocodingRepository.geocode(sportsFacility.info.address).onSuccess {
                if (it.values.isNotEmpty()) {
                    val cor = it.values.first().coordinate
                    _facilityWithCoordinate.value =
                        UiSportsFacilityWithCoordinate(sportsFacility.toUi(), cor.x, cor.y)
                }
            }
        }
    }

    fun searchFacility() {
        _listSportsFacilities.value =
            UiSportsFacilityList(
                _sportsFacilities.value?.filter {
                    it.facilityName.contains(
                        searchWord.value.toString(),
                    )
                } ?: emptyList(),
            )
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
