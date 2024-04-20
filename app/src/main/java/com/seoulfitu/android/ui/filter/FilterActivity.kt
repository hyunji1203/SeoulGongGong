package com.seoulfitu.android.ui.filter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seoulfitu.android.R
import com.seoulfitu.android.databinding.ActivityFilterBinding
import com.seoulfitu.android.domain.model.Town
import com.seoulfitu.android.ui.facility.SportsFacilityActivity

class FilterActivity : AppCompatActivity() {
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
            val selectedOptions =
                SelectedOptions(
                    cities = cityOptions,
                    facilities = facilityOptions,
                    rent = rentOptions,
                    parking = parkingOptions,
                )
            setResult(RESULT_OK, SportsFacilityActivity.getIntent(this, selectedOptions))
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
            addFilterOptionGroup(temp_facilities)
        }
        binding.cvFilterRent.apply {
            setFilterTitle(getString(R.string.filter_rent_option_title))
            addFilterOptionGroup(listOf("가능", "불가능"))
        }
        binding.cvFilterParking.apply {
            setFilterTitle(getString(R.string.filter_parking_option_title))
            addFilterOptionGroup(listOf("가능", "불가능"))
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
        private val temp_facilities =
            listOf(
                "풋살",
                "테니스장",
                "축구장",
                "족구장",
                "야구장",
                "수영장",
                "생활체육관",
                "배드민턴장",
                "농구장",
                "골프연습장",
                "게이트볼장",
                "기타",
            )

        fun getIntent(context: Context): Intent {
            return Intent(context, FilterActivity::class.java)
        }
    }
}
