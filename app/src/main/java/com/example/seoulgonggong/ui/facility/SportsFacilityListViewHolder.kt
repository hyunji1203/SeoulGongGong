package com.example.seoulgonggong.ui.facility

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seoulgonggong.databinding.ItemFacilityBinding
import com.example.seoulgonggong.ui.uimodel.UiSportsFacility

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
