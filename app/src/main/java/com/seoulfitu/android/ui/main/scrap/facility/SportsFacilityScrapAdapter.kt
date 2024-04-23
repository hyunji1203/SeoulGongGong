package com.seoulfitu.android.ui.main.scrap.facility

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.seoulfitu.android.ui.uimodel.UiSportsFacility

class SportsFacilityScrapAdapter(
    private val openFacility: (UiSportsFacility) -> Unit,
) : ListAdapter<UiSportsFacility, SportsFacilityScrapViewHolder>(diffUtil) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SportsFacilityScrapViewHolder = SportsFacilityScrapViewHolder.of(parent, openFacility)

    override fun onBindViewHolder(
        holder: SportsFacilityScrapViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil =
            object : DiffUtil.ItemCallback<UiSportsFacility>() {
                override fun areItemsTheSame(
                    oldItem: UiSportsFacility,
                    newItem: UiSportsFacility,
                ): Boolean {
                    return oldItem.idx == newItem.idx
                }

                override fun areContentsTheSame(
                    oldItem: UiSportsFacility,
                    newItem: UiSportsFacility,
                ): Boolean = oldItem == newItem
            }
    }
}
