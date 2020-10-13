package com.noemi.android.cars.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarDAO {

    @Query("SELECT * FROM car_table")
    suspend fun showCarList(): MutableList<CarEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCars2DB(cars: List<CarEntity>)

    @Query("SELECT * FROM car_table WHERE plateNumber LIKE:pNumber")
    suspend fun showFilteredCarsByPlateNumber(pNumber: String): MutableList<CarEntity>

    @Query("SELECT * FROM car_table WHERE batteryPercentage >= :percentage ORDER BY id")
    suspend fun showFilteredCarsByRemainingBattery(percentage: Int): MutableList<CarEntity>
}