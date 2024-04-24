package com.seoulfitu.android.ui.facility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seoulfitu.android.domain.repository.SportsFacilityRepository
import com.seoulfitu.android.ui.uimodel.UiSelectedOptions
import com.seoulfitu.android.ui.uimodel.UiSportsFacility
import com.seoulfitu.android.ui.uimodel.UiSportsFacilityList
import com.seoulfitu.android.ui.uimodel.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsFacilityViewModel @Inject constructor(
    private val repository: SportsFacilityRepository,
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

    private val _selectedOptions: MutableLiveData<UiSelectedOptions> = MutableLiveData()
    val selectedOptions: LiveData<UiSelectedOptions> = _selectedOptions

    fun getAllFacilities() {
        viewModelScope.launch {
            repository.getSportsFacility().onSuccess { facilities ->
                _sportsFacilities.value = facilities.map { it.toUi() }
                _listSportsFacilities.value =
                    UiSportsFacilityList(_sportsFacilities.value ?: emptyList())
            }
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
    }

    fun openFacilityDetail(item: UiSportsFacility) {
        _detailOpenEvent.value = item
    }

    fun setSelectedOption(options: UiSelectedOptions) {
        _selectedOptions.value = options
        _listSportsFacilities.value = UiSportsFacilityList(searchFacilityWithOptions(options))
    }

    private fun searchFacilityWithOptions(options: UiSelectedOptions): List<UiSportsFacility> {
        var selectedAnswer: List<UiSportsFacility> = _sportsFacilities.value ?: emptyList()

        // 자치구
        options.cities.forEach { city ->
            selectedAnswer = selectedAnswer.filter {
                it.address.contains(city)
            }
        }

        // 시설 종류
        options.facilities.forEach { facility ->
            selectedAnswer = selectedAnswer.filter {
                it.type.typeName.contains(facility)
            }
        }

        // 대관 -> 가능 / 불가능
        options.rent.forEach { rent ->
            selectedAnswer = selectedAnswer.filter {
                it.canRental.contains(rent)
            }
        }
        when (options.rent) {
            "가능" -> {
                selectedAnswer = selectedAnswer.filter {
                    it.canRental.contains("가능")
                }
            }

            "불가능" -> {
                selectedAnswer = selectedAnswer.filter {
                    it.canRental.contains("불가능")
                }
            }
        }

        // 주차 -> 주차 불가 / 없음
        when (options.parking) {
            "가능" -> {
                selectedAnswer = selectedAnswer.filter {
                    it.parkingInfo.run {
                        !(contains("주차 불가") || contains("없음"))
                    }
                }
            }

            "불가능" -> {
                selectedAnswer = selectedAnswer.filter {
                    it.parkingInfo.run {
                        (contains("주차 불가") || contains("없음"))
                    }
                }
            }
        }
        return selectedAnswer
    }
}
