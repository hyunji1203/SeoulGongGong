package com.seoulfitu.android.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.checkSelfPermission
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.seoulfitu.android.R
import com.seoulfitu.android.databinding.ActivityMainBinding
import com.seoulfitu.android.ui.facility.SportsFacilityActivity
import com.seoulfitu.android.util.openSetting
import com.seoulfitu.android.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val locationPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                val isGranted = it.value
                if (isGranted.not()) {
                    showToast(getString(R.string.main_location_access))
                    openSetting()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        subscribe()
        setClickListeners()
        setForecast()
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
    }

    private fun subscribe() {
        viewModel.throwable.observe(this) {
            showToast(getString(R.string.network_errer_message))
        }
    }

    private fun setClickListeners() {
        binding.clMainPublicFacility.setOnClickListener {
            startActivity(SportsFacilityActivity.getIntent(this))
        }
        binding.clMainPublicService.setOnClickListener {
            // 공공 서비스 이동 intent 넣으면 됨
        }
    }

    private fun checkLocationPermission() {
        if (checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationPermissionRequest.launch(locationPermissions)
            return
        }
    }

    private fun setForecast() {
        checkLocationPermission()
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        Log.d("test", "estsetsesetetsets")
        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let { viewModel.fetchWeather(it.latitude, location.longitude) }
                    location?.let { viewModel.fetchParticulateMatter(it.latitude, location.longitude) }
                }
        }
    }

    companion object {
        private val locationPermissions =
            arrayOf(
                ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION,
            )

        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
