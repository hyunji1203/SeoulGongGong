package com.seoulfitu.android.ui.common.bindingadapter

import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.seoulfitu.android.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@BindingAdapter("serviceImg")
fun ImageView.serviceImg(url: String) {
//    Glide.with(this).load(url).into(object : CustomTarget<Drawable>() {
//        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
//            this@serviceImg.setImageDrawable(resource)
//        }
//
//        override fun onLoadCleared(placeholder: Drawable?) {
//            TODO("Not yet implemented")
//        }
//
//    })
}

@BindingAdapter(value = ["serviceRvItemOperatingStartTime", "serviceRvItemOperatingEndTime"], requireAll = true)
fun TextView.serviceRvItemOperatingTime(startTime: String, endTime: String) {
    this.text = context.getString(R.string.sports_service_rv_item_operating_time, startTime, endTime)
}

@BindingAdapter(value = ["serviceDetailOperatingStartTime", "serviceDetailOperatingEndTime"], requireAll = true)
fun TextView.serviceDetailOperatingTime(startTime: String, endTime: String) {
    this.text = context.getString(R.string.sports_service_detail_operating_time, startTime, endTime)
}

@BindingAdapter("serviceStatusColorForStatus")
fun TextView.serviceStatusColorForStatus(status: String) {
    if (status.contains("중")) {
        setTextColor(ContextCompat.getColor(this.context, R.color.main_teal))
    } else {
        setTextColor(ContextCompat.getColor(this.context, R.color.red))
    }
}

@BindingAdapter("onClickServiceLink")
fun TextView.onClickServiceLink(url: String) {
    this.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}

@BindingAdapter("onClickServicePhoneNumber")
fun TextView.onClickServicePhoneNumber(phoneNumber: String) {
    val prefixTel = "tel:"
    val formattedPhoneNumber = phoneNumber.replace("-", "")
    val tel = "$prefixTel$formattedPhoneNumber"
    this.setOnClickListener {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(tel))
        context.startActivity(intent)
    }
}

@BindingAdapter(value = ["serviceDetailRegistrationStartTime", "serviceDetailRegistrationEndTime"], requireAll = true)
fun TextView.serviceDetailRegistrationDate(startDate:String, endDate:String){
    val startDateWithoutMills = startDate.split(".")[0]
    val endDateWithoutMills = endDate.split(".")[0]
    val localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val startLocalDateTime = LocalDateTime.parse(startDateWithoutMills, localDateTimeFormatter)
    val endLocalDateTime = LocalDateTime.parse(endDateWithoutMills, localDateTimeFormatter)
    val stringFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
    val formattedStartTime = startLocalDateTime.format(stringFormatter)
    val formattedEndTime = endLocalDateTime.format(stringFormatter)
    this.text = context.getString(R.string.sports_service_detail_registration_time, formattedStartTime, formattedEndTime)
}
