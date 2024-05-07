package com.seoulfitu.seoulfitu.ui.common.bindingadapter

import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.seoulfitu.seoulfitu.BuildConfig
import com.seoulfitu.seoulfitu.R

@BindingAdapter("app:setScrapStatus")
fun ImageView.setScrapStatus(isScrap: Boolean) {
    backgroundTintList = if (isScrap) {
        ContextCompat.getColorStateList(this.context, R.color.red)
    } else ContextCompat.getColorStateList(this.context, R.color.gray)
}

@BindingAdapter("app:onClickAddress")
fun TextView.setOnClickAddress(address: String?) {
    if (address == null) return
    val intent = Intent(
        Intent.ACTION_VIEW, Uri.parse(
            "nmap://search?query=${address}&${BuildConfig.APPLICATION_ID}"
        )
    )
    this.setOnClickListener {
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        runCatching {
            this.context.startActivity(intent)
        }.onFailure {
            this.context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=com.nhn.android.nmap")
                )
            )
        }
    }
}
