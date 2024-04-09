package com.example.seoulgonggong.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.checkSelfPermission
import com.example.seoulgonggong.R
import com.example.seoulgonggong.databinding.ActivityMainBinding
import com.example.seoulgonggong.util.openSetting
import com.example.seoulgonggong.util.showToast
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

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
        checkLocationPermission()
        subscribe()
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
    }

    private fun subscribe() {
        viewModel.weatherStatus.observe(this) { status ->
            Log.d("test", "ss $status")
            when (status) {
                "맑음" -> binding.ivMainWeatherIcon.setImageResource(R.drawable.ic_sum)
                "구름많음" -> binding.ivMainWeatherIcon.setImageResource(R.drawable.ic_little_sunny)
                "흐림" -> binding.ivMainWeatherIcon.setImageResource(R.drawable.ic_cloud)
                "비" -> binding.ivMainWeatherIcon.setImageResource(R.drawable.ic_rain)
                "눈" -> binding.ivMainWeatherIcon.setImageResource(R.drawable.ic_snow)
            }
        }
    }

    private fun checkLocationPermission() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationPermissionRequest.launch(locationPermissions)
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener {
            Log.d("test", "${it.latitude}, ${it.longitude}")
            viewModel.fetchWeather(it.latitude, it.longitude)
//            setTown(it.latitude, it.longitude)
        }
    }

    // 수정해야함!!!!!!!!!!
    private fun setTown(
        latitude: Double,
        longitude: Double,
    ) {
        val geocoder = Geocoder(applicationContext, Locale.KOREAN)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocation(
                latitude,
                longitude,
                1,
            ) { address ->
                if (address.size != 0) {
                    Log.d("test", "geocoder ${address[0].thoroughfare}")
//                    binding.tvMainTown.text = address[0].getAddressLine(0).toString()
                }
            }
        } else {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null) {
                Log.d("test", "geocoder ${addresses[0].thoroughfare}")
//                binding.tvMainTown.text = addresses[0].getAddressLine(0).toString()
            }
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
