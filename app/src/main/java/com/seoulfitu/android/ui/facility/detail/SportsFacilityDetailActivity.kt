package com.seoulfitu.android.ui.facility.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seoulfitu.android.BuildConfig
import com.seoulfitu.android.R
import com.seoulfitu.android.databinding.ActivitySportsFacilityDetailBinding
import com.seoulfitu.android.ui.uimodel.UiSportsFacility
import com.seoulfitu.android.util.getParcelableExtraCompat

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
    }

    private fun initIntentData() {
        val facility = intent.getParcelableExtraCompat<UiSportsFacility>(FACILITY_KEY)
        if (facility != null) {
            viewModel.setFacilityData(facility)
        }
    }

    private fun initObserve() {
        initOpenNaverMapObserve()
        initOpenWebPageObserve()
        initOpenPhoneDialObserve()
    }

    private fun initOpenNaverMapObserve() {
        viewModel.openNaverMap.observe(this) {
            if (it != null) {
                openNaverMap(getNaverMapIntent(it))
            }
        }
    }

    private fun getNaverMapIntent(address: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getNaverMapUrl(address)))
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        return intent
    }

    private fun openNaverMap(intent: Intent) {
        runCatching {
            startActivity(intent)
        }.onFailure {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(NAVER_MAP_PLAY_STORE_URL)
                )
            )
        }
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

    companion object {
        private const val FACILITY_KEY = "FACILITY_KEY"
        private const val NAVER_MAP_PLAY_STORE_URL = "market://details?id=com.nhn.android.nmap"
        private fun getNaverMapUrl(address: String) =
            "nmap://search?query=${address}&${BuildConfig.APPLICATION_ID}"

        fun getIntent(context: Context, facility: UiSportsFacility): Intent {
            val intent = Intent(context, SportsFacilityDetailActivity::class.java)
            intent.putExtra(FACILITY_KEY, facility)
            return intent
        }
    }
}
