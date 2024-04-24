package com.seoulfitu.android.ui.sports_service_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.seoulfitu.android.data.ERROR_MESSAGE_FAIL_RESULT
import com.seoulfitu.android.databinding.ActivitySportsServiceListBinding
import com.seoulfitu.android.ui.sports_service_detail.SportsServiceDetailActivity
import com.seoulfitu.android.ui.sports_service_list.viewmodel.SportsServiceListViewModel
import com.seoulfitu.android.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportsServiceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySportsServiceListBinding
    private val viewModel: SportsServiceListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        observeUiState()
        setUpView()
        viewModel.getServices()
    }

    private fun initBinding() {
        binding = ActivitySportsServiceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun observeUiState() {
        viewModel.uiState.observe(this) {
            when (it.isSuccess) {
                true -> {
                    binding.rvSportsServiceList.adapter = SportsServiceAdapter(it.result) { sportsService ->
                        SportsServiceDetailActivity.start(this, sportsService)
                    }
                }

                false -> {
                    showToast(it.errorMessage ?: ERROR_MESSAGE_FAIL_RESULT)
                }

                else -> {
                    // todo: 로딩 화면
                }
            }
        }
    }

    private fun setUpView() {
        binding.etSportsServiceListSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.searchData(text.toString())
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SportsServiceListActivity::class.java)
        }
    }
}