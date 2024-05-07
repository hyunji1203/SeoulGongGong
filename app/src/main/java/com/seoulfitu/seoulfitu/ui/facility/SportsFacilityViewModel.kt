package com.seoulfitu.seoulfitu.ui.facility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.seoulfitu.domain.repository.GeocodingRepository
import com.seoulfitu.seoulfitu.domain.repository.FacilityScrapRepository
import com.seoulfitu.seoulfitu.domain.repository.SportsFacilityRepository
import com.seoulfitu.seoulfitu.ui.uimodel.UiAvailabilityFilter
import com.seoulfitu.seoulfitu.ui.uimodel.UiSelectedOptions
import com.seoulfitu.seoulfitu.ui.uimodel.UiSportsFacility
import com.seoulfitu.seoulfitu.ui.uimodel.UiSportsFacilityList
import com.seoulfitu.seoulfitu.ui.uimodel.UiSportsFacilityWithCoordinate
import com.seoulfitu.seoulfitu.ui.uimodel.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
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
                        val isFacilityScraped = isScraped(facility.info.facilityName)
                        facility.toUi(isFacilityScraped)
                    }
                _listSportsFacilities.value =
                    UiSportsFacilityList(_sportsFacilities.value ?: emptyList())
                searchPosition()
            }
        }
    }

    private suspend fun isScraped(facilityName: String): Boolean {
        return withContext(Dispatchers.IO) {
            val scrapedFacilities = scrapRepository.getAll()
            scrapedFacilities.any { it.info.facilityName == facilityName }
        }
    }

    private suspend fun searchPosition() {
        viewModelScope.launch {
            _sportsFacilities.value?.map { sportsFacility ->
                async { sportsFacility to geocodingRepository.geocode(sportsFacility.address) }
            }?.awaitAll()?.forEach { (sportsFacility, addresses) ->
                addresses.onSuccess { address ->
                    if (address.values.isNotEmpty()) {
                        val cor = address.values.first().coordinate
                        _facilityWithCoordinate.value =
                            UiSportsFacilityWithCoordinate(sportsFacility, cor.x, cor.y)
                    }
                }
            }
            _facilityWithCoordinate.value = null
        }
    }

    fun refreshAllFacilities() {
        viewModelScope.launch {
            val currentFacility = _sportsFacilities.value?.map {
                it.copy(isScrap = isScraped(it.facilityName))
            } ?: emptyList()
            _sportsFacilities.value = currentFacility
        }
    }

    fun refreshListFacilities() {
        viewModelScope.launch {
            val currentFacility = _listSportsFacilities.value?.items?.map {
                it.copy(isScrap = isScraped(it.facilityName))
            } ?: emptyList()
            _listSportsFacilities.value = _listSportsFacilities.value?.copy(items = currentFacility)
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
        val selectedAnswer: List<UiSportsFacility> = _sportsFacilities.value ?: emptyList()
        if (options == null) return selectedAnswer

        return selectedAnswer.filter { facility ->
            val cityFit = if (options.cities.isEmpty()) true else options.cities.any { city ->
                facility.address.contains(city)
            }

            val typeFit = if (options.facilities.isEmpty()) true else options.facilities.any {
                facility.type.typeName == it
            }

            val rentFit =
                if (options.rent == null) true else checkRentFilter(options.rent, facility)

            val parkingFit =
                if (options.parking == null) true else checkParkingFilter(options.parking, facility)

            cityFit and typeFit and rentFit and parkingFit
        }
    }

    private fun checkRentFilter(
        rent: UiAvailabilityFilter,
        facility: UiSportsFacility
    ) = when (rent) {
        UiAvailabilityFilter.POSSIBLE ->
            facility.canRental == UiAvailabilityFilter.POSSIBLE.filterName

        UiAvailabilityFilter.IMPOSSIBLE ->
            facility.canRental == UiAvailabilityFilter.IMPOSSIBLE.filterName
    }

    private fun checkParkingFilter(
        parking: UiAvailabilityFilter,
        facility: UiSportsFacility
    ) = when (parking) {
        UiAvailabilityFilter.POSSIBLE ->
            !facility.parkingInfo.contains(PARKING_IMPOSSIBLE)
                    && !facility.parkingInfo.contains(NO_PARKING)

        UiAvailabilityFilter.IMPOSSIBLE ->
            facility.parkingInfo.contains(PARKING_IMPOSSIBLE)
                    || facility.parkingInfo.contains(NO_PARKING)
    }

    companion object {
        private const val PARKING_IMPOSSIBLE = "주차 불가"
        private const val NO_PARKING = "없음"
    }
}
