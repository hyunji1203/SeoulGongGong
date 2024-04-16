package com.example.seoulgonggong.ui.feature.public_service_list.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.example.seoulgonggong.databinding.ActivityPublicServiceListBinding
import com.example.seoulgonggong.ui.feature.public_service_list.recyclerview.PublicServiceAdapter
import com.example.seoulgonggong.ui.feature.public_service_list.viewmodel.PublicServiceListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PublicServiceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPublicServiceListBinding
    private val viewModel: PublicServiceListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        observeUiState()
        setUpView()
        viewModel.getServices()
    }

    private fun initBinding() {
        binding = ActivityPublicServiceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun observeUiState() {
        viewModel.uiState.observe(this) {
            if (it.isSuccess == true) {
                binding.rvPublicServiceList.apply {
                    adapter = PublicServiceAdapter(it.result)
                }
            } else if (it.isSuccess == false) {
                Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpView() {
        binding.etSearchServiceList.doOnTextChanged { text, _, _, _ ->
            viewModel.searchData(text.toString())
        }
    }
}