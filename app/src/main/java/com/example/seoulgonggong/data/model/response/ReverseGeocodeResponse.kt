package com.example.seoulgonggong.data.model.response

import com.example.seoulgonggong.data.utils.DomainConvertible
import com.example.seoulgonggong.domain.model.Area
import com.example.seoulgonggong.domain.model.Coords
import com.example.seoulgonggong.domain.model.Region
import com.example.seoulgonggong.domain.model.Regions
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ReverseGeocodeResponse(
    @SerialName("status")
    val status: ReverseGeocodeStatus,
    @SerialName("results")
    val results: List<ReverseGeocodeResult>,
): DomainConvertible<Regions> {
    override fun toDomain(): Regions {
        return Regions(
            values = this.results.map {
                Region(
                    area0 = Area(
                        name = it.region.area0.name,
                        coords = Coords(
                            crs = it.region.area0.coords.center.crs,
                            x = it.region.area0.coords.center.x,
                            y = it.region.area0.coords.center.y,
                        )
                    ),
                    area1 = Area(
                        name = it.region.area1.name,
                        coords = Coords(
                            crs = it.region.area1.coords.center.crs,
                            x = it.region.area1.coords.center.x,
                            y = it.region.area1.coords.center.y,
                        )
                    ),
                    area2 = Area(
                        name = it.region.area2.name,
                        coords = Coords(
                            crs = it.region.area2.coords.center.crs,
                            x = it.region.area2.coords.center.x,
                            y = it.region.area2.coords.center.y,
                        )
                    ),
                    area3 = Area(
                        name = it.region.area3.name,
                        coords = Coords(
                            crs = it.region.area3.coords.center.crs,
                            x = it.region.area3.coords.center.x,
                            y = it.region.area3.coords.center.y,
                        )
                    ),
                    area4 = Area(
                        name = it.region.area4.name,
                        coords = Coords(
                            crs = it.region.area4.coords.center.crs,
                            x = it.region.area4.coords.center.x,
                            y = it.region.area4.coords.center.y,
                        )
                    )
                )
            }
        )
    }
}

@Serializable
data class ReverseGeocodeStatus(
    @SerialName("code")
    val code: Int,
    @SerialName("name")
    val name: String,
    @SerialName("message")
    val message: String,
)

@Serializable
data class ReverseGeocodeResult(
    @SerialName("name")
    val name:String,
    @SerialName("code")
    val code:ReverseGeocodeCode,
    @SerialName("region")
    val region:ReverseGeocodeRegion
)

@Serializable
data class ReverseGeocodeCode(
    @SerialName("id")
    val id:String,
    @SerialName("type")
    val type:String,
    @SerialName("mappingId")
    val mappingId:String
)

@Serializable
data class ReverseGeocodeRegion(
    @SerialName("area0")
    val area0:ReverseGeocodeArea,
    @SerialName("area1")
    val area1:ReverseGeocodeArea,
    @SerialName("area2")
    val area2:ReverseGeocodeArea,
    @SerialName("area3")
    val area3:ReverseGeocodeArea,
    @SerialName("area4")
    val area4:ReverseGeocodeArea
)

@Serializable
data class ReverseGeocodeArea(
    @SerialName("name")
    val name:String,
    @SerialName("coords")
    val coords:ReverseGeocodeCoords,
    @SerialName("alias")
    val alias:String = ""
)

@Serializable
data class ReverseGeocodeCoords(
    @SerialName("center")
    val center:ReverseGeocodeCenter
)

@Serializable
data class ReverseGeocodeCenter(
    @SerialName("crs")
    val crs:String,
    @SerialName("x")
    val x:Double,
    @SerialName("y")
    val y:Double
)