package com.seoulfitu.android.ui.common.bindingadapter

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.seoulfitu.android.R
import com.seoulfitu.android.ui.uimodel.UiWeather
import com.seoulfitu.android.ui.uimodel.UiWeather.Companion.INIT_VALUE
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

@BindingAdapter("app:setWeatherInfoVisibility")
fun ConstraintLayout.setWeatherInfoVisibility(weather: UiWeather) {
    if (weather.temperature == INIT_VALUE) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }
}

@BindingAdapter("app:setNoWeatherInfoVisibility")
fun LottieAnimationView.setNoWeatherInfoVisibility(weather: UiWeather) {
    if (weather.temperature == INIT_VALUE) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}
