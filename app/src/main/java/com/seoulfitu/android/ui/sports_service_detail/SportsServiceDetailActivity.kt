package com.seoulfitu.android.ui.sports_service_detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.seoulfitu.android.databinding.ActivitySportsServiceDetailBinding
import com.seoulfitu.android.ui.sports_service_detail.viewmodel.SportsServiceDetailViewModel
import com.seoulfitu.android.ui.uimodel.UiSportsService
import com.seoulfitu.android.util.getParcelableExtraCompat
import com.seoulfitu.android.util.showToast
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
        val sportsService = intent.getParcelableExtraCompat(EXTRA_KEY_SPORTS_SERVICE) ?: UiSportsService()
        viewModel.setSportsService(sportsService)
    }

    private fun observeSportsService() {
        viewModel.sportsService.observe(this) {
            when (it.isSuccess) {
                true -> {
                    binding.service = it.result
                }
                false -> {
                    showToast(it.errorMessage)
                }
                else -> {
                    // todo: 로딩화면
                }
            }
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