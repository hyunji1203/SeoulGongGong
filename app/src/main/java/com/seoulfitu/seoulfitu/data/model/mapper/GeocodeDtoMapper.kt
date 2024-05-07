package com.seoulfitu.seoulfitu.data.model.mapper

import com.seoulfitu.seoulfitu.data.model.response.GeocodeResponse
import com.seoulfitu.seoulfitu.domain.model.Address
import com.seoulfitu.seoulfitu.domain.model.AddressElement
import com.seoulfitu.seoulfitu.domain.model.Addresses
import com.seoulfitu.seoulfitu.domain.model.Coordinate

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
            coordinate = Coordinate(address.x.toDouble(), address.y.toDouble()),
            distance = address.distance
        )
    }
)
