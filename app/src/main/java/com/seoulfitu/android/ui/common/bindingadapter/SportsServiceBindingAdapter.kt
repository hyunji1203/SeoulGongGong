package com.seoulfitu.android.ui.common.bindingadapter

import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.seoulfitu.android.BuildConfig
import com.seoulfitu.android.R
import com.seoulfitu.android.ui.uimodel.UiSportsServiceType

@BindingAdapter("app:serviceIcon")
fun ImageView.setServiceIcon(type: UiSportsServiceType?) {
    val iconSrc = when (type) {
        UiSportsServiceType.FUTSAL -> R.drawable.ic_futsal
        UiSportsServiceType.TENNIS -> R.drawable.ic_tennis
        UiSportsServiceType.PING_PONG -> R.drawable.ic_ping_pong
        UiSportsServiceType.SOCCER -> R.drawable.ic_soccer
        UiSportsServiceType.GYM -> R.drawable.ic_gym
        UiSportsServiceType.FOOT_VOLLEYBALL -> R.drawable.ic_foot_volleyball
        UiSportsServiceType.BASEBALL -> R.drawable.ic_baseball
        UiSportsServiceType.BADMINTON -> R.drawable.ic_badminton
        UiSportsServiceType.VOLLEYBALL -> R.drawable.ic_volleyball
        UiSportsServiceType.BASKETBALL -> R.drawable.ic_basketball
        UiSportsServiceType.GOLF -> R.drawable.ic_golf
        UiSportsServiceType.ETC, null -> R.drawable.ic_etc
    }
    this.setImageResource(iconSrc)
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
