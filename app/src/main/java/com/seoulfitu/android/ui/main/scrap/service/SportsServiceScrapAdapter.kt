package com.seoulfitu.android.ui.main.scrap.service

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.seoulfitu.android.ui.uimodel.UiSportsFacility
import com.seoulfitu.android.ui.uimodel.UiSportsService

class SportsServiceScrapAdapter(
    private val openService: (UiSportsService) -> Unit,
) : ListAdapter<UiSportsService, SportsServiceScrapViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SportsServiceScrapViewHolder = SportsServiceScrapViewHolder.of(parent, openService)

    override fun onBindViewHolder(holder: SportsServiceScrapViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil =
            object : DiffUtil.ItemCallback<UiSportsService>() {
                override fun areItemsTheSame(
                    oldItem: UiSportsService,
                    newItem: UiSportsService,
                ): Boolean {
                    return oldItem.info.title == newItem.info.title
                }

                override fun areContentsTheSame(
                    oldItem: UiSportsService,
                    newItem: UiSportsService,
                ): Boolean = oldItem == newItem
            }
    }
}
