package com.seoulfitu.seoulfitu

import android.app.Application
import com.seoulfitu.seoulfitu.data.datasource.GeoDataSource
import com.seoulfitu.seoulfitu.data.datasource.GoogleGeoDataSource
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SeoulGongGongApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDataSources()
    }

    private fun initDataSources() {
        geoDataSource = GoogleGeoDataSource(applicationContext)
    }

    companion object DependencyContainer {
        lateinit var geoDataSource: GeoDataSource
    }
}
