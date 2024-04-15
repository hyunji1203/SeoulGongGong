package com.example.seoulgonggong.domain.repository

import com.example.seoulgonggong.data.model.response.GeocodeResponse
import com.example.seoulgonggong.data.model.response.ReverseGeocodeResponse

interface GeocodingRepository{
    fun geocode(query:String):Result<GeocodeResponse>
    fun reverseGeocode(coords:String):Result<ReverseGeocodeResponse>
}