package com.example.seoulgonggong.ui.filter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.seoulgonggong.databinding.ActivityFilterBinding
import com.example.seoulgonggong.domain.model.Town

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
            with(binding) {
                val cityOptions = cvFilterCity.getSelectedOptions()
                val facilityOptions = cvFilterFacility.clearSelection()
                val rentOptions = cvFilterRent.clearSelection()
                val parkingOptions = cvFilterParking.clearSelection()
                //
            }
        }
    }

    private fun initFilterOption() {
        binding.cvFilterCity.apply {
            setFilterTitle("자치구")
            addFilterOptionGroup(Town.values().map { it.townName })
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
}
