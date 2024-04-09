package com.example.seoulgonggong.ui.feature.public_service_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seoulgonggong.databinding.ActivityPublicServiceListBinding

class PublicServiceListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPublicServiceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}