package com.example.seoulgonggong.ui

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.seoulgonggong.R

@BindingAdapter("serviceImg")
fun ImageView.serviceImg(url: String) {
    Glide.with(this).load(url).into(object : CustomTarget<Drawable>() {
        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            this@serviceImg.setImageDrawable(resource)
        }

        override fun onLoadCleared(placeholder: Drawable?) {
            TODO("Not yet implemented")
        }

    })
}

@BindingAdapter(value = ["serviceRvItemOperatingStartTime", "serviceRvItemOperatingEndTime"], requireAll = true)
fun TextView.serviceRvItemOperatingTime(startTime:String, endTime:String){
    this.text = context.getString(R.string.sports_service_rv_item_operating_time, startTime, endTime)
}

@BindingAdapter(value = ["serviceDetailOperatingStartTime", "serviceDetailOperatingEndTime"], requireAll = true)
fun TextView.serviceDetailOperatingTime(startTime:String, endTime:String){
    this.text = context.getString(R.string.sports_service_detail_operating_time, startTime, endTime)
}

@BindingAdapter("serviceStatusColorForStatus")
fun TextView.serviceStatusColorForStatus(status:String){
    if (status.contains("중")){
        setTextColor(ContextCompat.getColor(this.context, R.color.main_teal))
    }else{
        setTextColor(ContextCompat.getColor(this.context, R.color.red))
    }
}