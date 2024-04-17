package com.example.seoulgonggong.ui.feature.sports_service_list.recyclerview

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.seoulgonggong.databinding.SportsServiceItemBinding
import com.example.seoulgonggong.ui.model.UiSportsService

class SportsServiceViewHolder private constructor(
    private val binding: SportsServiceItemBinding,
    private val dataSet: List<UiSportsService>,
    private val onClickItem: (UiSportsService) -> Unit,
) : ViewHolder(binding.root) {
    init {
        binding.clSportsServiceItem.setOnClickListener {
            onClickItem(dataSet[adapterPosition])
        }
    }

    fun bind(data: UiSportsService) {
        binding.uiPublicService = data
        Glide.with(binding.root).load(data.img).into(object : CustomTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                binding.ivPublicServiceItemImg.setImageDrawable(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                TODO("Not yet implemented")
            }

        })
    }

    companion object {
        fun create(parent: ViewGroup, dataSet:List<UiSportsService>, onClickItem: (UiSportsService) -> Unit): SportsServiceViewHolder {
            val binding = SportsServiceItemBinding.inflate(LayoutInflater.from(parent.context))
            return SportsServiceViewHolder(binding, dataSet, onClickItem)
        }
    }
}
