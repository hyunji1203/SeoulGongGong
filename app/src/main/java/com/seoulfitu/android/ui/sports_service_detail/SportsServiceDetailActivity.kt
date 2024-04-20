package com.seoulfitu.android.ui.sports_service_detail

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.seoulfitu.android.databinding.ActivitySportsServiceDetailBinding
import com.seoulfitu.android.ui.sports_service_detail.viewmodel.SportsServiceDetailViewModel
import com.seoulfitu.android.ui.uimodel.UiSportsService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportsServiceDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySportsServiceDetailBinding
    private val viewModel: SportsServiceDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySportsServiceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentExtra()
        observeSportsService()
    }

    @Suppress("DEPRECATION")
    private fun getIntentExtra() {
        val sportsService = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_KEY_SPORTS_SERVICE, UiSportsService::class.java) as UiSportsService
        } else {
            intent.getSerializableExtra(EXTRA_KEY_SPORTS_SERVICE) as UiSportsService
        }
        viewModel.setSportsService(sportsService)
        viewModel.reverseGeocode()
    }

    private fun observeSportsService() {
        viewModel.sportsService.observe(this) {
            binding.sportsService = it
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