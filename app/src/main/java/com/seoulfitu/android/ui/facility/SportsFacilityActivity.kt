package com.seoulfitu.android.ui.facility

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.seoulfitu.android.R
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.seoulfitu.android.databinding.ActivityPublicSportsFacilityBinding
import com.seoulfitu.android.ui.facility.detail.SportsFacilityDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportsFacilityActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityPublicSportsFacilityBinding
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private val viewModel: SportsFacilityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_public_sports_facility)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.getAllFacilities()
        initMap()

        viewModel.test.observe(this) { test ->
            if (test) {
                viewModel.sportsFacilities.value?.forEach {
                    val marker = Marker()
                    marker.position = LatLng(it.y, it.x)
                    marker.map = naverMap
                }
            }
        }

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        initOpenListObserve()
        initOpenDetailObserve()
    }

    private fun initMap() {
        val mapFragment: MapFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_map) as MapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initOpenListObserve() {
        viewModel.listOpenEvent.observe(this) {
            if (it) {
                val bottomSheet = SportsFacilityBottomSheetFragment()
                bottomSheet.show(supportFragmentManager, bottomSheet.tag)
            }
        }
    }

    private fun initOpenDetailObserve() {
        viewModel.detailOpenEvent.observe(this) {
            startActivity(SportsFacilityDetailActivity.getIntent(this, it))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부
                naverMap.locationTrackingMode = LocationTrackingMode.None
            } else {
                naverMap.locationTrackingMode = LocationTrackingMode.Follow // 현위치 버튼 컨트롤 활성
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onMapReady(map: NaverMap) {
        this.naverMap = map

        naverMap.uiSettings.isLocationButtonEnabled = true
        naverMap.locationSource = locationSource

        ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000

        private val PERMISSIONS =
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )

        fun getIntent(context: Context): Intent {
            return Intent(context, SportsFacilityActivity::class.java)
        }
    }
}
