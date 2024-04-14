package com.example.seoulgonggong.ui.facility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.seoulgonggong.databinding.FragmentSportsFacilityBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportsFacilityBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSportsFacilityBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SportsFacilityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentSportsFacilityBottomSheetBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        initAdapter()

        return binding.root
    }

    private fun initAdapter() {
        val adapter = SportsFacilityListAdapter(viewModel::openFacilityDetail)
        adapter.submitList(viewModel.listSportsFacilities.value)
        binding.rvFacilityList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
