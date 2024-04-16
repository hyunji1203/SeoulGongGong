package com.example.seoulgonggong.ui.feature.public_service_list.recyclerview

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.seoulgonggong.databinding.PublicServiceItemBinding
import com.example.seoulgonggong.ui.model.UiPublicService

class PublicServiceViewHolder private constructor(private val binding: PublicServiceItemBinding) :
    ViewHolder(binding.root) {
    fun bind(data: UiPublicService) {
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
        fun create(parent: ViewGroup): PublicServiceViewHolder {
            val binding = PublicServiceItemBinding.inflate(LayoutInflater.from(parent.context))
            return PublicServiceViewHolder(binding)
        }
    }
}
