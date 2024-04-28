package com.seoulfitu.android.ui.main.scrap.service

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seoulfitu.android.databinding.ItemFacilityScrapBinding
import com.seoulfitu.android.databinding.ItemServiceScrapBinding
import com.seoulfitu.android.ui.uimodel.UiSportsFacility
import com.seoulfitu.android.ui.uimodel.UiSportsService

class SportsServiceScrapViewHolder(
    private val binding: ItemServiceScrapBinding,
    openService: (UiSportsService) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.openServiceEvent = openService
    }

    fun bind(item: UiSportsService) {
        binding.serviceItem = item
    }

    companion object {
        fun of(
            parent: ViewGroup,
            openService: (UiSportsService) -> Unit,
        ): SportsServiceScrapViewHolder {
            val binding = ItemServiceScrapBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
            return SportsServiceScrapViewHolder(binding, openService)
        }
    }
}
