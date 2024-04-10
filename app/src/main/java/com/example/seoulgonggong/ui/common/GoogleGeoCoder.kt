package com.example.seoulgonggong.ui.common

import android.content.Context
import android.location.Geocoder
import android.os.Build
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Locale

class GoogleGeoCoder(private val context: Context) {
    fun getAddress(
        latitude: Double,
        longitude: Double,
    ): String {
        var ret = ""
        GlobalScope.launch {
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
        }
        return ret
    }
}
