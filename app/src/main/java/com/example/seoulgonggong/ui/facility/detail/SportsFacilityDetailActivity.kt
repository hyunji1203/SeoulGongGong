package com.example.seoulgonggong.ui.facility.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.seoulgonggong.BuildConfig
import com.example.seoulgonggong.R
import com.example.seoulgonggong.databinding.ActivitySportsFacilityDetailBinding
import com.example.seoulgonggong.ui.uimodel.UiSportsFacility
import com.example.seoulgonggong.util.getParcelableExtraCompat

class SportsFacilityDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySportsFacilityDetailBinding
    private val viewModel: SportsFacilityDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sports_facility_detail)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initIntentData()
        initOpenNaverMapObserve()
        initOpenPhoneDialObserve()
    }

    private fun initIntentData() {
        val facility = intent.getParcelableExtraCompat<UiSportsFacility>(FACILITY_KEY)
        if (facility != null) {
            viewModel.setFacilityData(facility)
        }
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
