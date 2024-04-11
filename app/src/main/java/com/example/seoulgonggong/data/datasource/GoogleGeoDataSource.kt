package com.example.seoulgonggong.data.datasource

import android.content.Context
import android.location.Geocoder
import android.os.Build
import android.util.Log
import java.util.Locale

class GoogleGeoDataSource(private val context: Context) : GeoDataSource {
    override suspend fun getAddressByPosition(
        latitude: Double,
        longitude: Double,
    ): String {
        var ret = ""
        val geocoder = Geocoder(context, Locale.KOREAN)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocation(
                latitude,
                longitude,
                1,
            ) { address ->
                if (address.size != 0) {
                    Log.d("test", "geocoder ${address[0].getAddressLine(0)}")
                    ret = address[0].getAddressLine(0)
                }
            }
        } else {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null) {
                Log.d("test", "geocoder ${addresses[0].getAddressLine(0)}")
                for (address in addresses) {
                    ret = addresses[0].getAddressLine(0)
                }
            }
        }
        return ret
    }
}
