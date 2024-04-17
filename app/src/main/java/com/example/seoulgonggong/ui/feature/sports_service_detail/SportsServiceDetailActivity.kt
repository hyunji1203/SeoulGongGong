package com.example.seoulgonggong.ui.feature.sports_service_detail

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seoulgonggong.databinding.ActivitySportsServiceDetailBinding
import com.example.seoulgonggong.ui.model.UiSportsService

class SportsServiceDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySportsServiceDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySportsServiceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sportsService = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_KEY_SPORTS_SERVICE, UiSportsService::class.java) as UiSportsService
        } else {
            intent.getSerializableExtra(EXTRA_KEY_SPORTS_SERVICE) as UiSportsService
        }
        binding.sportsService = sportsService
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