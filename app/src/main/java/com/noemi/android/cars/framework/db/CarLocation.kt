package com.noemi.android.cars.framework.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarLocation(
    val latitude: Double,
    val longitude: Double,
    val address: String
) : Parcelable {

    override fun toString(): String {
        return "CarLocation: latitude=$latitude, longitude=$longitude, address='$address'"
    }
}