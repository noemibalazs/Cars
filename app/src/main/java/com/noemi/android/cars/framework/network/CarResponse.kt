package com.noemi.android.cars.framework.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "plateNumber") val plateNumber: String,
    @Json(name = "location") val location: CarLocationResponse,
    @Json(name = "model") val model: CarModelResponse,
    @Json(name = "batteryPercentage") val batteryPercentage: Int,
    @Json(name = "batteryEstimatedDistance") val batteryEstimatedDistance: Int
)