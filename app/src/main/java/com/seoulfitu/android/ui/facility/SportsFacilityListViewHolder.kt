package com.seoulfitu.android.ui.facility

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seoulfitu.android.databinding.ItemFacilityBinding
import com.seoulfitu.android.ui.uimodel.UiSportsFacility

class SportsFacilityListViewHolder(
    private val binding: ItemFacilityBinding,
    openFacilityEvent: (UiSportsFacility) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.openFacilityEvent = openFacilityEvent
    }

    fun bind(item: UiSportsFacility) {
        binding.facilityItem = item
    }

    companion object {
        fun of(
            parent: ViewGroup,
            openFacilityEvent: (UiSportsFacility) -> Unit,
        ): SportsFacilityListViewHolder {
            val binding = ItemFacilityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
            return SportsFacilityListViewHolder(binding, openFacilityEvent)
        }
    }
}
