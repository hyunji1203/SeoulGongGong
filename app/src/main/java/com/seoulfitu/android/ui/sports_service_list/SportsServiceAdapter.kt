package com.seoulfitu.android.ui.sports_service_list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.seoulfitu.android.ui.uimodel.UiSportsService

class SportsServiceAdapter(
    private val onItemClick:(UiSportsService) -> Unit
) : ListAdapter<UiSportsService, SportsServiceViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SportsServiceViewHolder = SportsServiceViewHolder.of(parent, onItemClick)

    override fun onBindViewHolder(holder: SportsServiceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<UiSportsService>() {
            override fun areItemsTheSame(
                oldItem: UiSportsService,
                newItem: UiSportsService,
            ): Boolean {
                return oldItem.info.serviceId == newItem.info.serviceId
            }

            override fun areContentsTheSame(
                oldItem: UiSportsService,
                newItem: UiSportsService,
            ): Boolean = oldItem == newItem
        }
    }
}