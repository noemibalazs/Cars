package com.noemi.android.core.datasource

import com.noemi.android.core.domain.Car

interface CarDataSource {

    suspend fun addCarsToDB(cars: List<Car>)

    suspend fun showsCarsFromDB(): List<Car>

    suspend fun carsFilteredByPlateNumber(plate: String): List<Car>

    suspend fun carsFilteredByBatteryPercentage(battery: Int): List<Car>
}