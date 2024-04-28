package com.seoulfitu.android.ui.facility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.seoulfitu.android.databinding.FragmentSportsFacilityBottomSheetBinding
import com.seoulfitu.android.ui.filter.facility.SportsFacilityFilterActivity
import com.seoulfitu.android.ui.filter.facility.SportsFacilityFilterActivity.Companion.emptySelectedOptions
import com.seoulfitu.android.ui.uimodel.UiSelectedOptions
import com.seoulfitu.android.util.getParcelableExtraCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportsFacilityBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSportsFacilityBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SportsFacilityViewModel by activityViewModels()
    private lateinit var adapter: SportsFacilityListAdapter
    private var selectedOptions = emptySelectedOptions

    private val sportsFacilityActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == AppCompatActivity.RESULT_OK) {
                val uiSelectedOptions =
                    it.data?.getParcelableExtraCompat<UiSelectedOptions>(FILTER_KEY) ?: ""
                selectedOptions = uiSelectedOptions as UiSelectedOptions
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentSportsFacilityBottomSheetBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        initAdapter()
        subscribe()
        setClickListeners()
        return binding.root
    }

    private fun initAdapter() {
        adapter = SportsFacilityListAdapter(viewModel::openFacilityDetail)
        binding.rvFacilityList.adapter = adapter
    }

    private fun subscribe() {
        viewModel.listSportsFacilities.observe(this) {
            adapter.submitList(it.items)
        }
    }

    private fun setClickListeners() {
        binding.btnFacilityFilter.setOnClickListener {
            val intent = SportsFacilityFilterActivity.getIntent(requireContext(), selectedOptions)
            sportsFacilityActivityLauncher.launch(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FILTER_KEY = "filter"
    }
}
