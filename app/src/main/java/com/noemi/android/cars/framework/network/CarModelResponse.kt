package com.noemi.android.cars.framework.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarModelResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "photoUrl") val photoUrl: String
) {
    override fun toString(): String {
        return "CarModelResponse: id=$id, title='$title', photoUrl='$photoUrl'"
    }
}