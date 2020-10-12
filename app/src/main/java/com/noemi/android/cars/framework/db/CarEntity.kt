package com.noemi.android.cars.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.noemi.android.cars.presentation.util.CAR_TABLE

@Entity(tableName = CAR_TABLE)
data class CarEntity(
    @PrimaryKey
    val id: Int,
    val plateNumber: String,

    @TypeConverters(CarLocationConverter::class)
    val location: CarLocation,

    @TypeConverters(CarModelConverter::class)
    val model: CarModel,

    val batteryPercentage: Int,
    val batteryEstimatedDistance: Int
) {
    override fun toString(): String {
        return "CarEntity: id=$id, plateNumber='$plateNumber', location=$location, model=$model, batteryPercentage=$batteryPercentage, batteryEstimatedDistance=$batteryEstimatedDistance"
    }
}