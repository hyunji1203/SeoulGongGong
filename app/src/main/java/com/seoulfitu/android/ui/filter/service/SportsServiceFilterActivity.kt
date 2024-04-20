package com.seoulfitu.android.ui.filter.service

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seoulfitu.android.R
import com.seoulfitu.android.databinding.ActivitySportsServiceFilterBinding
import com.seoulfitu.android.domain.model.Town
import com.seoulfitu.android.ui.facility.SportsFacilityBottomSheetFragment.Companion.FILTER_KEY
import com.seoulfitu.android.ui.filter.facility.SportsFacilityFilterActivity
import com.seoulfitu.android.ui.uimodel.UiSelectedOptions
import com.seoulfitu.android.ui.uimodel.UiSportsFacilityType

class SportsServiceFilterActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySportsServiceFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySportsServiceFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()
        initFilterOption()
    }

    private fun setClickListeners() {
        binding.ivServiceFilterReset.setOnClickListener {
            resetFilter()
        }
        binding.btnServiceFilterList.setOnClickListener {
            val cityOptions = binding.cvServiceFilterCity.getSelectedOptions()
            val typeOptions = binding.cvServiceFilterType.getSelectedOptions()
            val priceOptions = binding.cvServiceFilterPrice.getSelectedOptions()
            val parkingOptions = binding.cvServiceFilterParking.getSelectedOptions()
            val statusOptions = binding.cvServiceFilterStatus.getSelectedOptions()
            val uiSelectedOptions =
                UiSelectedOptions(
                    cities = cityOptions,
                    facilities = typeOptions,
                    price = priceOptions,
                    parking = parkingOptions,
                    serviceStatus = statusOptions,
                )
            setResult(RESULT_OK, getIntent(this, uiSelectedOptions))
            finish()
        }
    }

    private fun initFilterOption() {
        binding.cvServiceFilterCity.apply {
            setFilterTitle(getString(R.string.filter_city_option_title))
            addFilterOptionGroup(Town.entries.map { it.townName })
        }
        binding.cvServiceFilterType.apply {
            setFilterTitle(getString(R.string.filter_service_option_title))
            addFilterOptionGroup(UiSportsFacilityType.entries.map { it.typeName })
        }
        binding.cvServiceFilterPrice.apply {
            setFilterTitle(getString(R.string.filter_price_option_title))
            addFilterOptionGroup(options)
        }
        binding.cvServiceFilterParking.apply {
            setFilterTitle(getString(R.string.filter_parking_option_title))
            addFilterOptionGroup(options)
        }
        binding.cvServiceFilterStatus.apply {
            setFilterTitle(getString(R.string.filter_status_option_title))
            addFilterOptionGroup(options)
        }
    }

    private fun resetFilter() {
        with(binding) {
            cvServiceFilterCity.clearSelection()
            cvServiceFilterType.clearSelection()
            cvServiceFilterPrice.clearSelection()
            cvServiceFilterParking.clearSelection()
            cvServiceFilterStatus.clearSelection()
        }
    }

    companion object {
        private val options = listOf("가능", "불가능")

        fun getIntent(context: Context): Intent {
            return Intent(context, SportsFacilityFilterActivity::class.java)
        }

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
