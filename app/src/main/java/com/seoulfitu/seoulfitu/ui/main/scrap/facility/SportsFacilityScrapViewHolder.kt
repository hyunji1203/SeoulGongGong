package com.seoulfitu.seoulfitu.ui.main.scrap.facility

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seoulfitu.seoulfitu.databinding.ItemFacilityScrapBinding
import com.seoulfitu.seoulfitu.ui.uimodel.UiSportsFacility

class SportsFacilityScrapViewHolder(
    private val binding: ItemFacilityScrapBinding,
    openFacility: (UiSportsFacility) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.openFacilityEvent = openFacility
    }

    fun bind(item: UiSportsFacility) {
        binding.facilityItem = item
    }

    companion object {
        fun of(
            parent: ViewGroup,
            openFacility: (UiSportsFacility) -> Unit,
        ): SportsFacilityScrapViewHolder {
            val binding = ItemFacilityScrapBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
            return SportsFacilityScrapViewHolder(binding, openFacility)
        }
    }
}
