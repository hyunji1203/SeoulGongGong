package com.seoulfitu.android.ui.filter.facility

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seoulfitu.android.R
import com.seoulfitu.android.databinding.ActivitySportsFacilityFilterBinding
import com.seoulfitu.android.domain.model.Town
import com.seoulfitu.android.ui.facility.SportsFacilityBottomSheetFragment.Companion.FILTER_KEY
import com.seoulfitu.android.ui.uimodel.UiAvailabilityFilter.Companion.changeToAvailabilityFilter
import com.seoulfitu.android.ui.uimodel.UiAvailabilityFilter.Companion.getOptions
import com.seoulfitu.android.ui.uimodel.UiSelectedOptions
import com.seoulfitu.android.ui.uimodel.UiSportsFacilityType
import com.seoulfitu.android.util.getParcelableExtraCompat

class SportsFacilityFilterActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySportsFacilityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySportsFacilityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()
        initFilterOption()
    }

    private fun setClickListeners() {
        binding.ivFacilityFilterReset.setOnClickListener {
            resetFilter()
        }
        binding.btnFacilityFilterList.setOnClickListener {
            val cityOptions = binding.cvFacilityFilterCity.getSelectedOptions()
            val typeOptions = binding.cvFacilityFilterType.getSelectedOptions()
            val rentOptions = binding.cvFacilityFilterRent.getSelectedOption()
            val parkingOptions = binding.cvFacilityFilterParking.getSelectedOption()
            val uiSelectedOptions =
                UiSelectedOptions(
                    cities = cityOptions,
                    facilities = typeOptions,
                    rent = changeToAvailabilityFilter(rentOptions),
                    parking = changeToAvailabilityFilter(parkingOptions),
                )
            setResult(RESULT_OK, getIntent(this, uiSelectedOptions))
            finish()
        }
    }

    private fun initFilterOption() {
        val selected =
            intent.getParcelableExtraCompat(FILTER_KEY) ?: UiSelectedOptions()
        binding.cvFacilityFilterCity.apply {
            setFilterTitle(getString(R.string.filter_city_option_title))
            addFilterOptionGroup(Town.entries.map { it.townName }, selected.cities)
        }
        binding.cvFacilityFilterType.apply {
            setFilterTitle(getString(R.string.filter_facility_option_title))
            addFilterOptionGroup(UiSportsFacilityType.entries.map { it.typeName }, selected.facilities)
        }
        binding.cvFacilityFilterRent.apply {
            setFilterTitle(getString(R.string.filter_rent_option_title))
            addFilterOptionGroup(getOptions(), listOf(selected.rent?.filterName ?: ""))
        }
        binding.cvFacilityFilterParking.apply {
            setFilterTitle(getString(R.string.filter_parking_option_title))
            addFilterOptionGroup(getOptions(), listOf(selected.parking?.filterName ?: ""))
        }
    }

    private fun resetFilter() {
        with(binding) {
            cvFacilityFilterCity.clearSelection()
            cvFacilityFilterType.clearSelection()
            cvFacilityFilterRent.clearSelection()
            cvFacilityFilterParking.clearSelection()
        }
    }

    companion object {

        fun getIntent(
            context: Context,
            uiSelectedOptions: UiSelectedOptions,
        ): Intent {
            return Intent(context, SportsFacilityFilterActivity::class.java).apply {
                putExtra(FILTER_KEY, uiSelectedOptions)
            }
        }
    }
}
