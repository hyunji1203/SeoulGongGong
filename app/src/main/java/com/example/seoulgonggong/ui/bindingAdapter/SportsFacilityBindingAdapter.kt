package com.example.seoulgonggong.ui.bindingAdapter

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.seoulgonggong.R

@BindingAdapter("app:loadFacilityIcon")
fun ImageView.loadFacilityIcon(facilityType: String?) {
    if (facilityType == null) return
    val iconSrc = when (facilityType) {
        "수영장" -> R.drawable.ic_swimming
        "야구장" -> R.drawable.ic_baseball
        "축구장" -> R.drawable.ic_soccer
        "농구장" -> R.drawable.ic_basketball
        "테니스장" -> R.drawable.ic_tennis
        "배트민턴장" -> R.drawable.ic_badminton
        "골프연습장" -> R.drawable.ic_golf
        "빙상장" -> R.drawable.ic_ice_rink
        "게이트볼" -> R.drawable.ic_gateball
        "족구장" -> R.drawable.ic_foot_volleyball
        "풋살장" -> R.drawable.ic_futsal
        "생활체육관" -> R.drawable.ic_gym
        else -> R.drawable.ic_etc
    }
    this.setImageResource(iconSrc)
}

@BindingAdapter("app:setTextColorWithOperatingStatus")
fun TextView.setTextColorWithOperatingStatus(operatingStatus: String?) {
    if (operatingStatus == null) return
    if (operatingStatus.contains("운영")) this.setTextColor(
        ContextCompat.getColor(this.context, R.color.main_teal)
    ) else this.setTextColor(
        ContextCompat.getColor(this.context, R.color.red)
    )
}
