package com.seoulfitu.android.ui.facility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.android.domain.model.SportsFacility
import com.seoulfitu.android.domain.repository.GeocodingRepository
import com.seoulfitu.android.domain.repository.FacilityScrapRepository
import com.seoulfitu.android.domain.repository.SportsFacilityRepository
import com.seoulfitu.android.ui.uimodel.UiAvailabilityFilter
import com.seoulfitu.android.ui.uimodel.UiSelectedOptions
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

    private val _selectedOptions: MutableLiveData<UiSelectedOptions?> = MutableLiveData()
    val selectedOptions: LiveData<UiSelectedOptions?> = _selectedOptions

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

                _sportsFacilities.value?.forEach { searchPosition(it) }
//                _facilityWithCoordinate.value = null
            }
        }
    }

    private suspend fun isScraped(sportsFacility: SportsFacility): Boolean {
        return withContext(Dispatchers.IO) {
            val scrapedFacilities = scrapRepository.getAll()
            scrapedFacilities.any { it.info.facilityName == sportsFacility.info.facilityName }
        }
    }

    private suspend fun searchPosition(sportsFacility: UiSportsFacility) {
        viewModelScope.launch {
            geocodingRepository.geocode(sportsFacility.address).onSuccess {
                if (it.values.isNotEmpty()) {
                    val cor = it.values.first().coordinate
                    _facilityWithCoordinate.value =
                        UiSportsFacilityWithCoordinate(sportsFacility, cor.x, cor.y)
                }
            }
            _facilityWithCoordinate.value = null
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
        _listSportsFacilities.value =
            UiSportsFacilityList(searchFacilityWithOptions(_selectedOptions.value))
        _listOpenEvent.value = true
    }

    fun openFacilityDetail(item: UiSportsFacility) {
        _detailOpenEvent.value = item
    }

    fun setSelectedOption(options: UiSelectedOptions) {
        _selectedOptions.value = options
        _listSportsFacilities.value = UiSportsFacilityList(searchFacilityWithOptions(options))
    }

    private fun searchFacilityWithOptions(options: UiSelectedOptions?): List<UiSportsFacility> {
        var selectedAnswer: List<UiSportsFacility> = _sportsFacilities.value ?: emptyList()
        if (options == null) return selectedAnswer

        if (options.cities.isNotEmpty())
            selectedAnswer = checkAddressFilter(selectedAnswer, options)

        if (options.facilities.isNotEmpty())
            selectedAnswer = checkFacilityTypeFilter(selectedAnswer, options)

        if (options.rent != null)
            selectedAnswer = checkRentFilter(selectedAnswer, options)

        if (options.parking != null)
            selectedAnswer = checkParkingFilter(selectedAnswer, options)

        return selectedAnswer
    }

    private fun checkAddressFilter(
        selectedAnswer: List<UiSportsFacility>,
        options: UiSelectedOptions
    ): List<UiSportsFacility> = selectedAnswer.filter { facility ->
        options.cities.any { city ->
            facility.address.contains(city)
        }
    }

    private fun checkFacilityTypeFilter(
        selectedAnswer: List<UiSportsFacility>,
        options: UiSelectedOptions
    ): List<UiSportsFacility> = selectedAnswer.filter { facility ->
        options.facilities.any {
            facility.type.typeName == it
        }
    }

    private fun checkRentFilter(
        selectedAnswer: List<UiSportsFacility>,
        options: UiSelectedOptions
    ): List<UiSportsFacility> = selectedAnswer.filter {
        when (options.rent) {
            UiAvailabilityFilter.POSSIBLE ->
                it.canRental == UiAvailabilityFilter.POSSIBLE.filterName
            UiAvailabilityFilter.IMPOSSIBLE ->
                it.canRental == UiAvailabilityFilter.IMPOSSIBLE.filterName
            else -> true
        }
    }

    private fun checkParkingFilter(
        selectedAnswer: List<UiSportsFacility>,
        options: UiSelectedOptions
    ): List<UiSportsFacility> = selectedAnswer.filter { facility ->
        when (options.parking) {
            UiAvailabilityFilter.POSSIBLE ->
                !facility.parkingInfo.contains(PARKING_IMPOSSIBLE)
                        && !facility.parkingInfo.contains(NO_PARKING)
            UiAvailabilityFilter.IMPOSSIBLE ->
                facility.parkingInfo.contains(PARKING_IMPOSSIBLE)
                        || facility.parkingInfo.contains(NO_PARKING)
            else -> true
        }
    }

    companion object {
        private const val PARKING_IMPOSSIBLE = "주차 불가"
        private const val NO_PARKING = "없음"
    }
}
