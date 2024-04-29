package com.seoulfitu.android.ui.facility

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import com.seoulfitu.android.R
import com.seoulfitu.android.databinding.ActivityPublicSportsFacilityBinding
import com.seoulfitu.android.ui.facility.detail.SportsFacilityDetailActivity
import com.seoulfitu.android.ui.uimodel.UiSportsFacilityMarker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportsFacilityActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityPublicSportsFacilityBinding
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private val viewModel: SportsFacilityViewModel by viewModels()
    private val markers: MutableList<UiSportsFacilityMarker> = mutableListOf()
    private var selectedMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_public_sports_facility)

        viewModel.getAllFacilities()
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        initBinding()
        initMap()
        initObserver()
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshAllFacilities()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.onBackClick = { finish() }
    }

    private fun initMap() {
        val mapFragment: MapFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_map) as MapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initObserver() {
        initFacilitiesObserve()
        initOpenListObserve()
        initOpenDetailObserve()
        initMarker()
    }

    private fun initFacilitiesObserve() {
        viewModel.sportsFacilities.observe(this) { facilities ->
            markers.forEach { marker ->
                facilities.find { it.facilityName == marker.facility.facilityName }
                    ?.let { marker.facility = it }
            }
            selectedMarker?.performClick()
        }
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

    private fun initMarker() {
        viewModel.facilityWithCoordinate.observe(this) { data ->
            if (data != null) {
                val marker = Marker().apply {
                    position = LatLng(data.y, data.x)
                    icon = MarkerIcons.BLACK
                    setMarkerColor()
                    map = naverMap
                }
                val facilityMarker = UiSportsFacilityMarker(marker, data.facility)
                setMarkerClickListener(facilityMarker)
                markers.add(facilityMarker)
            } else {
                binding.icLoadingFacility.visibility = View.GONE
            }
        }
    }

    private fun setMarkerClickListener(facilityMarker: UiSportsFacilityMarker) {
        facilityMarker.marker.setOnClickListener {
            selectedMarker?.setMarkerColor(isClicked = false)
            selectedMarker = facilityMarker.marker
            facilityMarker.marker.setMarkerColor(isClicked = true)
            binding.bottomFacilityInfo.apply {
                setInfoItem(facilityMarker.facility)
                setClickEvent(viewModel::openFacilityDetail)
            }
            true
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
        naverMap.setOnMapClickListener { _, _ -> clearMarkerClick() }

        ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE)
    }

    private fun clearMarkerClick() {
        if (selectedMarker != null) {
            selectedMarker?.setMarkerColor(isClicked = false)
            binding.bottomFacilityInfo.visibility = View.GONE
            selectedMarker = null
        }
    }

    private fun Marker.setMarkerColor(isClicked: Boolean = false) {
        if (isClicked) this.iconTintColor = getColor(R.color.red)
        else this.iconTintColor = getColor(R.color.main_teal)
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
