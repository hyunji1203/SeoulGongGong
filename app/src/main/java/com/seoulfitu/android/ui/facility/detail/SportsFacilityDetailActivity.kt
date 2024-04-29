package com.seoulfitu.android.ui.facility.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seoulfitu.android.R
import com.seoulfitu.android.databinding.ActivitySportsFacilityDetailBinding
import com.seoulfitu.android.ui.common.bindingadapter.setScrapStatus
import com.seoulfitu.android.ui.uimodel.UiSportsFacility
import com.seoulfitu.android.util.getParcelableExtraCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportsFacilityDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySportsFacilityDetailBinding
    private val viewModel: SportsFacilityDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sports_facility_detail)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initIntentData()
        initObserve()
        setClickListeners()
    }

    private fun initIntentData() {
        val facility = intent.getParcelableExtraCompat<UiSportsFacility>(FACILITY_KEY)
        if (facility != null) {
            viewModel.setFacilityData(facility)
        }
    }

    private fun initObserve() {
        initOpenWebPageObserve()
        initOpenPhoneDialObserve()
        initOpenFacilityObserve()
    }

    private fun initOpenWebPageObserve() {
        viewModel.openWebPage.observe(this) {
            if (it != null) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }
        }
    }

    private fun initOpenPhoneDialObserve() {
        viewModel.openPhoneDial.observe(this) {
            if (it != null) {
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(it)))
            }
        }
    }

    private fun initOpenFacilityObserve() {
        viewModel.facility.observe(this) {
            if (it.isScrap) binding.btnFacilityDetailScrap.setScrapStatus(true)
            else binding.btnFacilityDetailScrap.setScrapStatus(false)
        }
    }

    private fun setClickListeners() {
        binding.btnFacilityDetailScrap.setOnClickListener {
            viewModel.scrapFacility()
        }
    }

    companion object {
        private const val FACILITY_KEY = "FACILITY_KEY"

        fun getIntent(context: Context, facility: UiSportsFacility): Intent {
            val intent = Intent(context, SportsFacilityDetailActivity::class.java)
            intent.putExtra(FACILITY_KEY, facility)
            return intent
        }
    }
}
