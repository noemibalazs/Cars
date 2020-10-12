package com.noemi.android.core.domain

import java.io.Serializable

data class Location(
    val latitude: Double,
    val longitude: Double,
    val address: String
) : Serializable {

    override fun toString(): String {
        return "Location is: latitude=$latitude, longitude=$longitude, address='$address"
    }
}