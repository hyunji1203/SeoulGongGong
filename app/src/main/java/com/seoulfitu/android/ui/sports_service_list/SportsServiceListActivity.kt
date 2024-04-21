package com.seoulfitu.android.ui.sports_service_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.seoulfitu.android.databinding.ActivitySportsServiceListBinding
import com.seoulfitu.android.ui.sports_service_detail.SportsServiceDetailActivity
import com.seoulfitu.android.ui.sports_service_list.viewmodel.SportsServiceListViewModel
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
                    binding.rvSportsServiceList.apply {
                        adapter = SportsServiceAdapter(it.result) { sportsService ->
                            SportsServiceDetailActivity.start(context, sportsService)
                        }
                    }
                }

                false -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
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
}