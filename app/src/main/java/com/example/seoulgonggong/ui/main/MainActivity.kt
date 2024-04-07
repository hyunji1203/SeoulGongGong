package com.example.seoulgonggong.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.checkSelfPermission
import com.example.seoulgonggong.R
import com.example.seoulgonggong.databinding.ActivityMainBinding
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val locationPermissionRequest =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
        ) { permissions ->
            when {
                permissions.getOrDefault(ACCESS_FINE_LOCATION, false) -> {
                    checkLocationPermission()
                }

                permissions.getOrDefault(ACCESS_COARSE_LOCATION, false) -> {
                    checkLocationPermission()
                }

                else -> {
                    Toast.makeText(
                        this,
                        getString(R.string.main_location_access),
                        Toast.LENGTH_SHORT,
                    ).show()
                    val intent =
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts("package", packageName, null)
                        }
                    startActivity(intent)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        checkLocationPermission()
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
    }

    private fun checkLocationPermission() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(
                this,
                ACCESS_COARSE_LOCATION,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionRequest.launch(locationPermissions)
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener {
            viewModel.fetchWeather(it.latitude, it.longitude)
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
