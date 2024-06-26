package com.seoulfitu.seoulfitu.ui.main

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager.PERMISSION_DENIED
import android.location.Location
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.seoulfitu.seoulfitu.R
import com.seoulfitu.seoulfitu.databinding.ActivityMainBinding
import com.seoulfitu.seoulfitu.ui.facility.SportsFacilityActivity
import com.seoulfitu.seoulfitu.ui.facility.detail.SportsFacilityDetailActivity.Companion.getIntent
import com.seoulfitu.seoulfitu.ui.main.scrap.facility.SportsFacilityScrapAdapter
import com.seoulfitu.seoulfitu.ui.main.scrap.service.SportsServiceScrapAdapter
import com.seoulfitu.seoulfitu.ui.sports_service_detail.SportsServiceDetailActivity
import com.seoulfitu.seoulfitu.ui.sports_service_list.SportsServiceListActivity
import com.seoulfitu.seoulfitu.util.openSetting
import com.seoulfitu.seoulfitu.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var serviceScrapAdapter: SportsServiceScrapAdapter
    private lateinit var facilityScrapAdapter: SportsFacilityScrapAdapter
    private var isLocationPermissionGranted: Boolean = false

    private val locationPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                isLocationPermissionGranted = true
                setForecast()
            } else {
                showToast(getString(R.string.main_location_access))
                openSetting()
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
    }

    override fun onStart() {
        super.onStart()
        if (isLocationPermissionGranted) setForecast()
        else requestLocationPermission()
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

    private fun requestLocationPermission() {
        locationPermissionRequest.launch(ACCESS_FINE_LOCATION)
    }

    private fun setForecast() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PERMISSION_DENIED) {
            isLocationPermissionGranted = true
            fusedLocationClient.getCurrentLocation(
                createCurrentLocationRequest(),
                createCancellationToken()
            ).addOnSuccessListener { location: Location? ->
                location?.let {
                    viewModel.fetchWeather(it.latitude, it.longitude)
                    viewModel.fetchParticulateMatter(it.latitude, it.longitude)
                }
            }.addOnCanceledListener {
                showToast(getString(R.string.main_location_failure_message))
            }
        }
    }

    private fun createCancellationToken(): CancellationToken =
        object : CancellationToken() {
            override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken {
                return CancellationTokenSource().token
            }
            override fun isCancellationRequested(): Boolean = false
        }

    private fun createCurrentLocationRequest(): CurrentLocationRequest {
        return CurrentLocationRequest.Builder()
            .setDurationMillis(LIMIT_TIME.toLong())
            .setMaxUpdateAgeMillis(CACHING_EXPIRES_IN.toLong())
            .setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY)
            .build()
    }

    private fun setScrapList() {
        viewModel.fetchSportsFacilityScrap()
        viewModel.fetchSportsServiceScrap()
    }

    companion object {
        private const val LIMIT_TIME = 50000
        private const val CACHING_EXPIRES_IN = 3600000
    }
}
