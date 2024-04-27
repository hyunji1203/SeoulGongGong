package com.seoulfitu.android.ui.sports_service_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.seoulfitu.android.databinding.ActivitySportsServiceDetailBinding
import com.seoulfitu.android.ui.common.bindingadapter.setScrapStatus
import com.seoulfitu.android.ui.sports_service_detail.viewmodel.SportsServiceDetailViewModel
import com.seoulfitu.android.ui.uimodel.UiSportsService
import com.seoulfitu.android.util.getParcelableExtraCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportsServiceDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySportsServiceDetailBinding
    private val viewModel: SportsServiceDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySportsServiceDetailBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)
        getIntentExtra()
        observeSportsService()
        setClickListeners()
    }

    private fun getIntentExtra() {
        val sportsService = intent.getParcelableExtraCompat(EXTRA_KEY_SPORTS_SERVICE) ?: UiSportsService()
        viewModel.setSportsService(sportsService)
    }

    private fun observeSportsService() {
        viewModel.service.observe(this) {
            binding.service = it
            setScrapStatue()
        }
    }

    private fun setClickListeners(){
        binding.ivSportsServiceDetailScrap.setOnClickListener {
            viewModel.scrapService()
        }
    }

    private fun setScrapStatue() {
        if (viewModel.service.value?.scrapped == true) {
            binding.ivSportsServiceDetailScrap.setScrapStatus(true)
        } else {
            binding.ivSportsServiceDetailScrap.setScrapStatus(false)
        }
    }

    companion object {
        private const val EXTRA_KEY_SPORTS_SERVICE = "SPORTS_SERVICE"
        fun start(context: Context, sportsService: UiSportsService) {
            val intent = Intent(context, SportsServiceDetailActivity::class.java).apply {
                putExtra(EXTRA_KEY_SPORTS_SERVICE, sportsService)
            }
            context.startActivity(intent)
        }
    }
}