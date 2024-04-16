package com.example.seoulgonggong.ui.filter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.seoulgonggong.databinding.ActivityFilterBinding
import com.example.seoulgonggong.domain.model.Town
import com.example.seoulgonggong.ui.facility.SportsFacilityActivity

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
            setFilterTitle("자치구")
            addFilterOptionGroup(Town.values().map { it.townName })
        }
        binding.cvFilterFacility.apply {
            setFilterTitle("시설 종류")
            addFilterOptionGroup(temp_facilities)
        }
        binding.cvFilterRent.apply {
            setFilterTitle("대관")
            addFilterOptionGroup(listOf("가능", "불가능"))
        }
        binding.cvFilterParking.apply {
            setFilterTitle("주차")
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
        private val temp_facilities = listOf("풋살", "테니스장", "축구장", "족구장", "야구장", "수영장")
    }
}
