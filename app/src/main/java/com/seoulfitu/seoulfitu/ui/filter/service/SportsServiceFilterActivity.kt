package com.seoulfitu.seoulfitu.ui.filter.service

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seoulfitu.seoulfitu.R
import com.seoulfitu.seoulfitu.databinding.ActivitySportsServiceFilterBinding
import com.seoulfitu.seoulfitu.domain.model.Town
import com.seoulfitu.seoulfitu.ui.facility.SportsFacilityBottomSheetFragment.Companion.FILTER_KEY
import com.seoulfitu.seoulfitu.ui.uimodel.UiSelectedOptions
import com.seoulfitu.seoulfitu.ui.uimodel.UiSportsServiceType
import com.seoulfitu.seoulfitu.util.getParcelableExtraCompat

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
            val priceOptions = binding.cvServiceFilterPrice.getSelectedOption()
            val statusOptions = binding.cvServiceFilterStatus.getSelectedOptions()
            val uiSelectedOptions =
                UiSelectedOptions(
                    cities = cityOptions,
                    services = typeOptions,
                    price = priceOptions,
                    serviceStatus = statusOptions,
                )
            setResult(RESULT_OK, getIntent(this, uiSelectedOptions))
            finish()
        }
    }

    private fun initFilterOption() {
        val selected =
            intent.getParcelableExtraCompat(FILTER_KEY) ?: UiSelectedOptions()
        binding.cvServiceFilterCity.apply {
            setFilterTitle(getString(R.string.filter_city_option_title))
            addFilterOptionGroup(Town.entries.map { it.townName }, selected.cities)
        }
        binding.cvServiceFilterType.apply {
            setFilterTitle(getString(R.string.filter_service_option_title))
            addFilterOptionGroup(UiSportsServiceType.entries.map { it.typeName }, selected.services)
        }
        binding.cvServiceFilterPrice.apply {
            setFilterTitle(getString(R.string.filter_price_option_title))
            addFilterOptionGroup(priceOptions, listOf(selected.price))
        }
        binding.cvServiceFilterStatus.apply {
            setFilterTitle(getString(R.string.filter_status_option_title))
            addFilterOptionGroup(statusOptions, selected.serviceStatus)
        }
    }

    private fun resetFilter() {
        with(binding) {
            cvServiceFilterCity.clearSelection()
            cvServiceFilterType.clearSelection()
            cvServiceFilterPrice.clearSelection()
            cvServiceFilterStatus.clearSelection()
        }
    }

    companion object {
        private val priceOptions = listOf("유료", "무료")
        private val statusOptions = listOf("접수중", "접수 종료", "예약 마감", "예약 일시중지", "안내중")

        fun getIntent(
            context: Context,
            uiSelectedOptions: UiSelectedOptions,
        ): Intent {
            return Intent(context, SportsServiceFilterActivity::class.java).apply {
                putExtra(FILTER_KEY, uiSelectedOptions)
            }
        }
    }
}
