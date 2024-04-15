package com.example.seoulgonggong.data.model.response

import com.example.seoulgonggong.data.utils.DomainConvertible
import com.example.seoulgonggong.domain.model.Address
import com.example.seoulgonggong.domain.model.AddressElement
import com.example.seoulgonggong.domain.model.Addresses
import com.example.seoulgonggong.domain.model.Coordinate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodeResponse(
    @SerialName("status")
    val status: String,
    @SerialName("meta")
    val meta: GeocodeMeta,
    @SerialName("addresses")
    val addresses: List<GeocodeAddress>,
    @SerialName("errorMessage")
    val errorMessage: String
) : DomainConvertible<Addresses> {
    override fun toDomain(): Addresses {
        return Addresses(
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
    }
}

@Serializable
data class GeocodeMeta(
    @SerialName("totalCount")
    val totalCount: Int,
    @SerialName("page")
    val page: Int,
    @SerialName("count")
    val count: Int,
)

@Serializable
data class GeocodeAddress(
    @SerialName("roadAddress")
    val roadAddress: String,
    @SerialName("jibunAddress")
    val jibunAddress: String,
    @SerialName("englishAddress")
    val englishAddress: String,
    @SerialName("addressElements")
    val addressElements: List<GeocodeAddressElement>,
    @SerialName("x")
    val x: String,
    @SerialName("y")
    val y: String,
    @SerialName("distance")
    val distance: Double
)

@Serializable
data class GeocodeAddressElement(
    @SerialName("types")
    val types: List<String>,
    @SerialName("longName")
    val longName: String,
    @SerialName("shortName")
    val shortName: String,
    @SerialName("code")
    val code: String
)