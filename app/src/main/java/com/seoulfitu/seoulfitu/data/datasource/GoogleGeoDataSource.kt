package com.seoulfitu.seoulfitu.data.datasource

import android.content.Context
import android.location.Geocoder
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GoogleGeoDataSource
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) : GeoDataSource {
        override suspend fun getAddressByPosition(
            latitude: Double,
            longitude: Double,
        ): String {
            return suspendCoroutine { continuation ->
                val geocoder = Geocoder(context, Locale.KOREAN)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    geocoder.getFromLocation(latitude, longitude, 1) { address ->
                        if (address.size != 0) {
                            continuation.resume(address[0].getAddressLine(0))
                        }
                    }
                } else {
                    val address = geocoder.getFromLocation(latitude, longitude, 1)
                    if (address != null) {
                        continuation.resume(address[0].getAddressLine(0))
                    }
                }
            }
        }
    }
