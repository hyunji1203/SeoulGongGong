package com.example.seoulgonggong.ui.bindingAdapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.seoulgonggong.R
import com.example.seoulgonggong.ui.uimodel.UiSportsFacilityType

@BindingAdapter("app:loadFacilityIcon")
fun ImageView.loadFacilityIcon(facilityType: UiSportsFacilityType) {
    val iconSrc = when (facilityType) {
        UiSportsFacilityType.SWIMMING -> R.drawable.ic_swimming
        UiSportsFacilityType.BASEBALL -> R.drawable.ic_baseball
        UiSportsFacilityType.SOCCER -> R.drawable.ic_soccer
        UiSportsFacilityType.BASKETBALL -> R.drawable.ic_basketball
        UiSportsFacilityType.TENNIS -> R.drawable.ic_tennis
        UiSportsFacilityType.BADMINTON -> R.drawable.ic_badminton
        UiSportsFacilityType.GOLF -> R.drawable.ic_golf
        UiSportsFacilityType.ICE_RINK -> R.drawable.ic_ice_rink
        UiSportsFacilityType.GATEBALL -> R.drawable.ic_gateball
        UiSportsFacilityType.FOOT_VOLLEYBALL -> R.drawable.ic_foot_volleyball
        UiSportsFacilityType.FUTSAL -> R.drawable.ic_futsal
        UiSportsFacilityType.GYM -> R.drawable.ic_gym
        else -> R.drawable.ic_etc
    }
    this.setImageResource(iconSrc)
}

@BindingAdapter("app:setTextColorWithOperatingStatus")
fun TextView.setTextColorWithOperatingStatus(operatingStatus: String) {
    if (operatingStatus.contains("운영")) this.setTextColor(
        ContextCompat.getColor(this.context, R.color.main_teal)
    ) else this.setTextColor(
        ContextCompat.getColor(this.context, R.color.red)
    )
}

@BindingAdapter("app:setNoNumberText")
fun TextView.setNoNumberText(phoneNumber: String) {
    if (phoneNumber.length < 3) {
        text = "홈페이지 참조"
        setTextColor(
            ContextCompat.getColor(this.context, R.color.black)
        )
    }
}

@BindingAdapter("app:setNoItemsMessageVisibility")
fun TextView.setNoItemsMessageVisibility(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}
