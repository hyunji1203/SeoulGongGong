package com.example.seoulgonggong.ui.feature.public_service_list.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.seoulgonggong.databinding.ActivityPublicServiceListBinding
import com.example.seoulgonggong.ui.feature.public_service_list.recyclerview.PublicServiceAdapter
import com.example.seoulgonggong.ui.feature.public_service_list.viewmodel.PublicServiceListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PublicServiceListActivity : AppCompatActivity() {

    lateinit var binding: ActivityPublicServiceListBinding
    val viewModel: PublicServiceListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPublicServiceListBinding.inflate(layoutInflater)
        viewModel.uiState.observe(this) {
            if (it.isSuccess == true) {
                binding.rvPublicServiceList.apply {
                    adapter = PublicServiceAdapter(it.result)
                }
            } else if (it.isSuccess == false) {
                Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
        setContentView(binding.root)
        viewModel.getData()
    }

    private fun initBinding() {

    }
}