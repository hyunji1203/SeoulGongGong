package com.seoulfitu.android.ui.filter.facility

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seoulfitu.android.R
import com.seoulfitu.android.databinding.ActivityFilterBinding
import com.seoulfitu.android.domain.model.Town
import com.seoulfitu.android.ui.facility.SportsFacilityBottomSheetFragment.Companion.FILTER_KEY
import com.seoulfitu.android.ui.uimodel.UiSelectedOptions
import com.seoulfitu.android.ui.uimodel.UiSportsFacilityType

class FacilityFilterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()
        initFilterOption()
    }

    private fun setClickListeners() {
        binding.ivFilterReset.setOnClickListener {
            resetFilter()
        }
        binding.btnFilterList.setOnClickListener {
            val cityOptions = binding.cvFilterCity.getSelectedOptions()
            val facilityOptions = binding.cvFilterFacility.getSelectedOptions()
            val rentOptions = binding.cvFilterRent.getSelectedOptions()
            val parkingOptions = binding.cvFilterParking.getSelectedOptions()
            val uiSelectedOptions =
                UiSelectedOptions(
                    cities = cityOptions,
                    facilities = facilityOptions,
                    rent = rentOptions,
                    parking = parkingOptions,
                )
            setResult(RESULT_OK, getIntent(this, uiSelectedOptions))
            finish()
        }
    }

    private fun initFilterOption() {
        binding.cvFilterCity.apply {
            setFilterTitle(getString(R.string.filter_city_option_title))
            addFilterOptionGroup(Town.entries.map { it.townName })
        }
        binding.cvFilterFacility.apply {
            setFilterTitle(getString(R.string.filter_facility_option_title))
            addFilterOptionGroup(UiSportsFacilityType.entries.map { it.typeName })
        }
        binding.cvFilterRent.apply {
            setFilterTitle(getString(R.string.filter_rent_option_title))
            addFilterOptionGroup(options)
        }
        binding.cvFilterParking.apply {
            setFilterTitle(getString(R.string.filter_parking_option_title))
            addFilterOptionGroup(options)
        }
    }

    private fun resetFilter() {
        with(binding) {
            cvFilterCity.clearSelection()
            cvFilterFacility.clearSelection()
            cvFilterRent.clearSelection()
            cvFilterParking.clearSelection()
        }
    }

    companion object {
        private val options = listOf("가능", "불가능")

        fun getIntent(context: Context): Intent {
            return Intent(context, FacilityFilterActivity::class.java)
        }

        fun getIntent(
            context: Context,
            uiSelectedOptions: UiSelectedOptions,
        ): Intent {
            return Intent(context, FacilityFilterActivity::class.java).apply {
                putExtra(FILTER_KEY, uiSelectedOptions)
            }
        }
    }
}
