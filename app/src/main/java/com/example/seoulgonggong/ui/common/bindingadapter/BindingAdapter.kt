package com.example.seoulgonggong.ui.common.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.seoulgonggong.R
import com.example.seoulgonggong.domain.model.WeatherStatus
import com.example.seoulgonggong.domain.model.WeatherStatus.CLOUD
import com.example.seoulgonggong.domain.model.WeatherStatus.LITTLE_SUNNY
import com.example.seoulgonggong.domain.model.WeatherStatus.RAIN
import com.example.seoulgonggong.domain.model.WeatherStatus.SNOW
import com.example.seoulgonggong.domain.model.WeatherStatus.SUN

@BindingAdapter("app:setWeatherIcon")
fun ImageView.setWeatherIcon(weatherStatus: WeatherStatus?) {
    when (weatherStatus) {
        SUN -> setImageResource(R.drawable.ic_sum)
        LITTLE_SUNNY -> setImageResource(R.drawable.ic_little_sunny)
        CLOUD -> setImageResource(R.drawable.ic_cloud)
        RAIN -> setImageResource(R.drawable.ic_rain)
        SNOW -> setImageResource(R.drawable.ic_snow)
        else -> setImageResource(R.drawable.ic_loading)
    }
}
