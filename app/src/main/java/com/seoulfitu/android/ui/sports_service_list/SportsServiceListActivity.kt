package com.seoulfitu.android.ui.sports_service_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.seoulfitu.android.data.ERROR_MESSAGE_FAIL_RESULT
import com.seoulfitu.android.databinding.ActivitySportsServiceListBinding
import com.seoulfitu.android.ui.filter.service.SportsServiceFilterActivity
import com.seoulfitu.android.ui.sports_service_detail.SportsServiceDetailActivity
import com.seoulfitu.android.ui.sports_service_list.viewmodel.SportsServiceListViewModel
import com.seoulfitu.android.ui.uimodel.UiSelectedOptions
import com.seoulfitu.android.util.getParcelableExtraCompat
import com.seoulfitu.android.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportsServiceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySportsServiceListBinding
    private val viewModel: SportsServiceListViewModel by viewModels()
    private lateinit var adapter: SportsServiceAdapter
    private val getFilterOptions =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val options = result.data?.getParcelableExtraCompat<UiSelectedOptions>(FILTER_KEY)!!
                viewModel.applyOptions(options)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        observeUiState()
        setUpView()
        initServiceList()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getServices()
    }

    private fun initBinding() {
        binding = ActivitySportsServiceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
    }

    private fun initServiceList() {
        adapter = SportsServiceAdapter { sportsService ->
            SportsServiceDetailActivity.start(this, sportsService)
        }
        binding.rvSportsServiceList.adapter = adapter
    }

    private fun observeUiState() {
        viewModel.uiState.observe(this) {
            when (it.isSuccess) {
                true -> {
                    adapter.submitList(it.result)
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
            viewModel.updateSearchKeyword(text.toString())
        }
        binding.onClickSearch = { viewModel.searchData() }
        binding.onClickFilter = {
            val intent = SportsServiceFilterActivity.getIntent(this, viewModel.uiState.value?.selectedOptions!!)
            getFilterOptions.launch(intent)
        }
    }

    companion object {
        private const val FILTER_KEY = "filter"

        fun getIntent(context: Context): Intent {
            return Intent(context, SportsServiceListActivity::class.java)
        }
    }
}