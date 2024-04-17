package com.seoulfitu.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SeoulGongGongApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDataSources()
    }

    private fun initDataSources() {
        geoDataSource =
            com.seoulfitu.android.data.datasource.GoogleGeoDataSource(applicationContext)
    }

    companion object DependencyContainer {
        lateinit var geoDataSource: com.seoulfitu.android.data.datasource.GeoDataSource
    }
}
