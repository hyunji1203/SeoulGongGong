package com.seoulfitu.android.data.model.mapper

import com.seoulfitu.android.data.model.response.ReverseGeocodeResponse
import com.seoulfitu.android.domain.model.Area
import com.seoulfitu.android.domain.model.Coords
import com.seoulfitu.android.domain.model.RegionWithCoordinate
import com.seoulfitu.android.domain.model.RegionsWithCoordinate

fun ReverseGeocodeResponse.toDomain() = RegionsWithCoordinate(
    values = this.results.map {
        RegionWithCoordinate(
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
