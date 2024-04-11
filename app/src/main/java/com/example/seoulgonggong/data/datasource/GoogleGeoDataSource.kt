package com.example.seoulgonggong.data.datasource

import android.content.Context
import android.location.Geocoder
import android.os.Build
import java.util.Locale
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GoogleGeoDataSource(private val context: Context) : GeoDataSource {
    override suspend fun getAddressByPosition(
        latitude: Double,
        longitude: Double,
    ): String {
        return suspendCoroutine { continuation ->
            val geocoder = Geocoder(context, Locale.KOREAN)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geocoder.getFromLocation(
                    latitude,
                    longitude,
                    1,
                ) { address ->
                    if (address.size != 0) {
                        continuation.resume(address[0].getAddressLine(0))
                    }
                }
            } else {
                val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                if (addresses != null) {
                    for (address in addresses) {
                        continuation.resume(addresses[0].getAddressLine(0))
                    }
                }
            }
        }
    }
}
