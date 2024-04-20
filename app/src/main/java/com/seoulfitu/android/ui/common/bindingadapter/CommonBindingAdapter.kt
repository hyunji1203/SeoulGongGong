package com.seoulfitu.android.ui.common.bindingadapter

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.seoulfitu.android.R

@BindingAdapter("app:setScrapStatus")
fun ImageView.setScrapStatus(isScrap: Boolean) {
    backgroundTintList = if (isScrap) {
        ContextCompat.getColorStateList(this.context, R.color.red)
    } else ContextCompat.getColorStateList(this.context, R.color.gray)
}
