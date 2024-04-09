package com.example.seoulgonggong.ui.feature.public_service_list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seoulgonggong.ui.model.PublicServiceUiModel

class PublicServiceAdapter(private val dataSet: List<PublicServiceUiModel>) : RecyclerView.Adapter<PublicServiceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicServiceViewHolder =
        PublicServiceViewHolder.create(parent)

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: PublicServiceViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }
}