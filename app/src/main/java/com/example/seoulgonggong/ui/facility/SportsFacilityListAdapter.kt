package com.example.seoulgonggong.ui.facility

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.seoulgonggong.ui.uimodel.UiSportsFacility

class SportsFacilityListAdapter(
    private val openFacilityEvent: (UiSportsFacility) -> Unit,
) : ListAdapter<UiSportsFacility, SportsFacilityListViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SportsFacilityListViewHolder = SportsFacilityListViewHolder.of(parent, openFacilityEvent)

    override fun onBindViewHolder(holder: SportsFacilityListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<UiSportsFacility>() {
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
