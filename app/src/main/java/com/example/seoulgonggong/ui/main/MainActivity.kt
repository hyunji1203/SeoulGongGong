package com.example.seoulgonggong.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.checkSelfPermission
import com.example.seoulgonggong.R
import com.example.seoulgonggong.databinding.ActivityMainBinding
import com.example.seoulgonggong.domain.model.WeatherStatus.CLOUD
import com.example.seoulgonggong.domain.model.WeatherStatus.LITTLE_SUNNY
import com.example.seoulgonggong.domain.model.WeatherStatus.RAIN
import com.example.seoulgonggong.domain.model.WeatherStatus.SNOW
import com.example.seoulgonggong.domain.model.WeatherStatus.SUN
import com.example.seoulgonggong.util.openSetting
import com.example.seoulgonggong.util.showToast
import com.google.android.gms.location.LocationServices
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
        setForecast()
        subscribe()
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
    }

    private fun subscribe() {
        viewModel.weatherInfo.observe(this) { weather ->
            when (weather.weatherStatus) {
                SUN -> binding.ivMainWeatherIcon.setImageResource(R.drawable.ic_sum)
                LITTLE_SUNNY -> binding.ivMainWeatherIcon.setImageResource(R.drawable.ic_little_sunny)
                CLOUD -> binding.ivMainWeatherIcon.setImageResource(R.drawable.ic_cloud)
                RAIN -> binding.ivMainWeatherIcon.setImageResource(R.drawable.ic_rain)
                SNOW -> binding.ivMainWeatherIcon.setImageResource(R.drawable.ic_snow)
            }
        }
        viewModel.throwable.observe(this) { error ->
            showToast(getString(R.string.network_errer_message))
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
        fusedLocationClient.lastLocation.addOnSuccessListener {
            viewModel.fetchWeather(it.latitude, it.longitude)
            viewModel.fetchParticulateMatter(it.latitude, it.longitude)
        }
    }

    companion object {
        private val locationPermissions =
            arrayOf(
                ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION,
            )
    }
}
