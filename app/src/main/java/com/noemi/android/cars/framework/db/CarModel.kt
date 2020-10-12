package com.noemi.android.cars.framework.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarModel(
    val id: Int,
    val title: String,
    val photoUrl: String
) : Parcelable {

    override fun toString(): String {
        return "CarModel: id=$id, title='$title', photoUrl='$photoUrl'"
    }
}