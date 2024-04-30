package com.seoulfitu.android.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.checkSelfPermission
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.seoulfitu.android.R
import com.seoulfitu.android.databinding.ActivityMainBinding
import com.seoulfitu.android.ui.facility.SportsFacilityActivity
import com.seoulfitu.android.ui.facility.detail.SportsFacilityDetailActivity.Companion.getIntent
import com.seoulfitu.android.ui.main.scrap.facility.SportsFacilityScrapAdapter
import com.seoulfitu.android.ui.main.scrap.service.SportsServiceScrapAdapter
import com.seoulfitu.android.ui.sports_service_detail.SportsServiceDetailActivity
import com.seoulfitu.android.ui.sports_service_list.SportsServiceListActivity
import com.seoulfitu.android.util.openSetting
import com.seoulfitu.android.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var serviceScrapAdapter: SportsServiceScrapAdapter
    private lateinit var facilityScrapAdapter: SportsFacilityScrapAdapter

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
        initAdapter()
        subscribe()
        setClickListeners()
        setForecast()
    }

    override fun onResume() {
        super.onResume()
        setScrapList()
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
    }

    private fun initAdapter() {
        facilityScrapAdapter = SportsFacilityScrapAdapter(viewModel::openFacilityDetail)
        serviceScrapAdapter = SportsServiceScrapAdapter(viewModel::openServiceDetail)
    }

    private fun subscribe() {
        viewModel.scrapedFacilities.observe(this) { scrapedFacilities ->
            binding.cvFacilityScrap.setAdapter(scrapedFacilities.isEmpty(), facilityScrapAdapter)
            facilityScrapAdapter.submitList(scrapedFacilities)
        }
        viewModel.scrapedServices.observe(this) { scrapedServices ->
            binding.cvServiceScrap.setAdapter(scrapedServices.isEmpty(), serviceScrapAdapter)
            serviceScrapAdapter.submitList(scrapedServices)
        }
        viewModel.facilityDetailOpenEvent.observe(this) {
            startActivity(getIntent(this, it))
        }
        viewModel.serviceDetailOpenEvent.observe(this) {
            SportsServiceDetailActivity.start(this, it)
        }
        viewModel.throwable.observe(this) {
            showToast(getString(R.string.network_errer_message))
        }
    }

    private fun setClickListeners() {
        binding.clMainPublicFacility.setOnClickListener {
            startActivity(SportsFacilityActivity.getIntent(this))
        }
        binding.clMainPublicService.setOnClickListener {
            startActivity(SportsServiceListActivity.getIntent(this))
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
        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let { viewModel.fetchWeather(it.latitude, location.longitude) }
                    location?.let { viewModel.fetchParticulateMatter(it.latitude, location.longitude) }
                }
        }
    }

    private fun setScrapList() {
        viewModel.fetchSportsFacilityScrap()
        viewModel.fetchSportsServiceScrap()
    }

    companion object {
        private val locationPermissions =
            arrayOf(
                ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION,
            )
    }
}
