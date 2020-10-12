package com.noemi.android.core.domain

import java.io.Serializable

data class Car(
    val id: Int,
    val plateNumber: String,
    val location: Location,
    val model: Model,
    val batteryPercentage: Int,
    val batteryEstimatedDistance: Int
) : Serializable {

    override fun toString(): String {
        return "Car: id=$id, plateNumber='$plateNumber', location=$location, model=$model, batteryPercentage=$batteryPercentage, batteryEstimatedDistance=$batteryEstimatedDistance"
    }
}