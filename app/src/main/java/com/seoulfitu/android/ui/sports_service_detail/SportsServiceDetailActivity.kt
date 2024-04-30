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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class SportsServiceDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySportsServiceDetailBinding
    private val viewModel: SportsServiceDetailViewModel by viewModels()
    private var flag = false

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
            val service = it.copy(
                info = it.info.copy(
                    registrationStartDate = formatRegistrationDate(it.info.registrationStartDate),
                    registrationEndDate = formatRegistrationDate(it.info.registrationEndDate)
                )
            )
            binding.service = service
            setScrapStatue()
        }
    }

    private fun setClickListeners() {
        binding.ivSportsServiceDetailScrap.setOnClickListener {
            viewModel.scrapService()
            flag = true
        }
        binding.ivSportsServiceDetailBack.setOnClickListener {
            if (flag) setResult(RESULT_OK)
            finish()
        }
    }

    private fun setScrapStatue() {
        if (viewModel.service.value?.scrapped == true) {
            binding.ivSportsServiceDetailScrap.setScrapStatus(true)
        } else {
            binding.ivSportsServiceDetailScrap.setScrapStatus(false)
        }
    }

    private fun formatRegistrationDate(date: String): String {
        // 자릿수 통일을 위해 밀리초 제거
        val dateWithoutMills = date.split(".")[0]
        val dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PARSING_PATTERN)
        val dateTime = LocalDateTime.parse(dateWithoutMills, dateTimeFormatter)
        val stringFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN)
        return dateTime.format(stringFormatter)
    }

    companion object {
        private const val EXTRA_KEY_SPORTS_SERVICE = "SPORTS_SERVICE"
        private const val DATE_TIME_PARSING_PATTERN = "yyyy-MM-dd HH:mm:ss"
        private const val DATE_TIME_FORMAT_PATTERN = "yyyy.MM.dd HH:mm"

        fun getIntent(context: Context, sportsService: UiSportsService): Intent {
            return Intent(context, SportsServiceDetailActivity::class.java).apply {
                putExtra(EXTRA_KEY_SPORTS_SERVICE, sportsService)
            }
        }

        fun start(context: Context, sportsService: UiSportsService) {
            val intent = Intent(context, SportsServiceDetailActivity::class.java).apply {
                putExtra(EXTRA_KEY_SPORTS_SERVICE, sportsService)
            }
            context.startActivity(intent)
        }
    }
}