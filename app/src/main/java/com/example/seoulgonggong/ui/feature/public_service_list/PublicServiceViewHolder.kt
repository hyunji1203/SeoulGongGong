package com.example.seoulgonggong.ui.feature.public_service_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.seoulgonggong.databinding.PublicServiceItemBinding
import com.example.seoulgonggong.ui.model.UiPublicService

class PublicServiceViewHolder private constructor(private val binding: PublicServiceItemBinding) :
    ViewHolder(binding.root) {
    fun bind(data: UiPublicService) {
        binding.tvPublicServiceItemTitle.text = data.title
        binding.tvPublicServiceItemPlace.text = data.place
        binding.tvPublicServiceItemOperatingTime.text = data.operatingTime
        Glide.with(binding.root)
            .load(data.img)
            .into(binding.ivPublicServiceItemImg)
    }

    companion object {
        fun create(parent: ViewGroup): PublicServiceViewHolder {
            val binding = PublicServiceItemBinding.inflate(LayoutInflater.from(parent.context))
            return PublicServiceViewHolder(binding)
        }
    }
}
