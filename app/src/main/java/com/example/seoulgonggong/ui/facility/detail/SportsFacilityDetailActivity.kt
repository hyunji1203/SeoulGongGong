package com.example.seoulgonggong.ui.facility.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.seoulgonggong.R
import com.example.seoulgonggong.databinding.ActivitySportsFacilityDetailBinding
import com.example.seoulgonggong.ui.uimodel.UiSportsFacility
import com.example.seoulgonggong.util.getParcelableExtraCompat

class SportsFacilityDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySportsFacilityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sports_facility_detail)

        binding.lifecycleOwner = this
        initIntentData()
    }

    private fun initIntentData() {
        val facility = intent.getParcelableExtraCompat<UiSportsFacility>(FACILITY_KEY)
        if (facility != null) {
            binding.facilityItem = facility
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
