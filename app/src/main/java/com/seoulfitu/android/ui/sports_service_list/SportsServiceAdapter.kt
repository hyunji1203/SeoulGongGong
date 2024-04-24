package com.seoulfitu.android.ui.sports_service_list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seoulfitu.android.ui.uimodel.UiSportsService

class SportsServiceAdapter(
    private val dataSet: List<UiSportsService>,
    private val onItemClick:(UiSportsService) -> Unit
    ) : RecyclerView.Adapter<SportsServiceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsServiceViewHolder =
        SportsServiceViewHolder.create(parent, onItemClick)

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: SportsServiceViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }
}