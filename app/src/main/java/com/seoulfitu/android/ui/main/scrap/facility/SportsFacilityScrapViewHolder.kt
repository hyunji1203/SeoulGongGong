package com.seoulfitu.android.ui.main.scrap.facility

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seoulfitu.android.databinding.ItemScrapBinding
import com.seoulfitu.android.ui.common.bindingadapter.loadFacilityIcon
import com.seoulfitu.android.ui.uimodel.UiSportsFacility

class SportsFacilityScrapViewHolder(
    private val binding: ItemScrapBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: UiSportsFacility) {
        binding.ivScrapItemIcon.loadFacilityIcon(item.type)
        binding.tvScrapItemName.text = item.facilityName
    }

    companion object {
        fun of(parent: ViewGroup): SportsFacilityScrapViewHolder {
            val binding =
                ItemScrapBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
            return SportsFacilityScrapViewHolder(binding)
        }
    }
}
