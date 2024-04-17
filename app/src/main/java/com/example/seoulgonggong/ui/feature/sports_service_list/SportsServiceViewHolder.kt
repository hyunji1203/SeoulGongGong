package com.example.seoulgonggong.ui.feature.sports_service_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.seoulgonggong.databinding.SportsServiceItemBinding
import com.example.seoulgonggong.ui.model.UiSportsService

class SportsServiceViewHolder private constructor(
    private val binding: SportsServiceItemBinding,
    onItemClick: (UiSportsService) -> Unit,
) : ViewHolder(binding.root) {
    init {
        binding.onItemClick = onItemClick
    }

    fun bind(data: UiSportsService) {
        binding.sportsService = data
    }

    companion object {
        fun create(parent: ViewGroup, onClickItem: (UiSportsService) -> Unit): SportsServiceViewHolder {
            val binding = SportsServiceItemBinding.inflate(LayoutInflater.from(parent.context))
            return SportsServiceViewHolder(binding, onClickItem)
        }
    }
}
