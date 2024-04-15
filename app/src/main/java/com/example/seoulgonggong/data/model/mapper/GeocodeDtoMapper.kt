package com.example.seoulgonggong.data.model.mapper

import com.example.seoulgonggong.data.model.response.GeocodeResponse
import com.example.seoulgonggong.domain.model.Address
import com.example.seoulgonggong.domain.model.AddressElement
import com.example.seoulgonggong.domain.model.Addresses

fun GeocodeResponse.toDomain() = Addresses(
    values = this.addresses.map { address ->
        Address(
            roadAddress = address.roadAddress,
            jibunAddress = address.jibunAddress,
            englishAddress = address.englishAddress,
            addressElements = address.addressElements.map {
                AddressElement(
                    types = it.types,
                    longName = it.longName,
                    shortName = it.shortName,
                    code = it.code
                )
            },
            x = address.x,
            y = address.y,
            distance = address.distance
        )
    }
)