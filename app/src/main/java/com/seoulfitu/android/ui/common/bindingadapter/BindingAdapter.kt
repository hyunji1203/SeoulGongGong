package com.seoulfitu.android.ui.common.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.seoulfitu.android.R
import com.seoulfitu.android.ui.uimodel.WeatherStatus

@BindingAdapter("app:setWeatherIcon")
fun ImageView.setWeatherIcon(weatherStatus: WeatherStatus?) {
    when (weatherStatus) {
        WeatherStatus.SUN -> setImageResource(R.drawable.ic_sum)
        WeatherStatus.LITTLE_SUNNY -> setImageResource(R.drawable.ic_little_sunny)
        WeatherStatus.CLOUD -> setImageResource(R.drawable.ic_cloud)
        WeatherStatus.RAIN -> setImageResource(R.drawable.ic_rain)
        WeatherStatus.SNOW -> setImageResource(R.drawable.ic_snow)
        else -> setImageResource(R.drawable.ic_loading)
    }
}
