package com.noemi.android.cars.framework.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarLocationResponse(
    @Json(name = "latitude") val latitude: Double,
    @Json(name = "longitude") val longitude: Double,
    @Json(name = "address") val address: String
) {
    override fun toString(): String {
        return "CarLocationResponse: latitude=$latitude, longitude=$longitude, address='$address'"
    }
}