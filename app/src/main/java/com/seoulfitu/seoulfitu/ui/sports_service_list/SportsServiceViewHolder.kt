package com.seoulfitu.seoulfitu.ui.sports_service_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seoulfitu.seoulfitu.databinding.SportsServiceItemBinding
import com.seoulfitu.seoulfitu.ui.uimodel.UiSportsService

class SportsServiceViewHolder(
    private val binding: SportsServiceItemBinding,
    onItemClick: (UiSportsService) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.onItemClick = onItemClick
    }

    fun bind(data: UiSportsService) {
        binding.service = data
    }

    companion object {
        fun of(
            parent: ViewGroup,
            onItemClick: (UiSportsService) -> Unit,
        ): SportsServiceViewHolder {
            val binding = SportsServiceItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
            return SportsServiceViewHolder(binding, onItemClick)
        }
    }
}
