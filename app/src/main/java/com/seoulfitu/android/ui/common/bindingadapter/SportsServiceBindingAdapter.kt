package com.seoulfitu.android.ui.common.bindingadapter

import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.seoulfitu.android.R

@BindingAdapter("serviceImg")
fun ImageView.serviceImg(url: String?) {
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

@BindingAdapter("serviceStatusColorForStatus")
fun TextView.serviceStatusColorForStatus(status: String?) {
    if (status?.contains("중") == true) {
        setTextColor(ContextCompat.getColor(this.context, R.color.main_teal))
    } else {
        setTextColor(ContextCompat.getColor(this.context, R.color.red))
    }
}

@BindingAdapter("onClickServiceLink")
fun TextView.onClickServiceLink(url: String?) {
    this.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}

@BindingAdapter("onClickServicePhoneNumber")
fun TextView.onClickServicePhoneNumber(phoneNumber: String?) {
    val prefixTel = "tel:"
    val formattedPhoneNumber = phoneNumber?.replace("-", "")
    val tel = "$prefixTel$formattedPhoneNumber"
    this.setOnClickListener {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(tel))
        context.startActivity(intent)
    }
}
