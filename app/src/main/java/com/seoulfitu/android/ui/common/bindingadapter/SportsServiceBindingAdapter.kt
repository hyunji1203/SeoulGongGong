package com.seoulfitu.android.ui.common.bindingadapter

import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.seoulfitu.android.BuildConfig
import com.seoulfitu.android.R

@BindingAdapter("app:serviceImg")
fun ImageView.setServiceImg(url: String?) {
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

@BindingAdapter("app:onClickServiceLocation")
fun TextView.setOnClickServiceLocationListener(location: String?) {
    this.setOnClickListener {
        val url = "nmap://search?query=%s&%s".format(
            location, BuildConfig.APPLICATION_ID
        )
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        context.startActivity(intent)
    }
}

@BindingAdapter("app:serviceStatusColorForStatus")
fun TextView.setServiceStatusTextColorForStatus(status: String?) {
    if (status?.contains("중") == true) {
        setTextColor(ContextCompat.getColor(this.context, R.color.main_teal))
    } else {
        setTextColor(ContextCompat.getColor(this.context, R.color.red))
    }
}

@BindingAdapter("app:onClickServiceLink")
fun TextView.setOnClickServiceLinkListener(url: String?) {
    this.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}

@BindingAdapter("app:onClickServicePhoneNumber")
fun TextView.setOnClickServicePhoneNumberListener(phoneNumber: String?) {
    this.setOnClickListener {
        val formattedPhoneNumber = phoneNumber?.replace("-", "")
        val telUri = "tel:%s".format(formattedPhoneNumber)
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(telUri))
        context.startActivity(intent)
    }
}
