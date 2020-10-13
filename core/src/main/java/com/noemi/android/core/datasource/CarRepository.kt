package com.noemi.android.core.datasource

import com.noemi.android.core.domain.Car

class CarRepository(private val carDataSource: CarDataSource) {

    suspend fun addCars2DB(cars: List<Car>) {
        carDataSource.addCarsToDB(cars)
    }

    suspend fun showCars(): List<Car> {
        return carDataSource.showsCarsFromDB()
    }

    suspend fun filteredCarsByPlateNumber(plateNumber: String): List<Car>{
        return carDataSource.carsFilteredByPlateNumber(plateNumber)
    }

    suspend fun filteredCarsByBatteryPercentage(batteryPercentage: Int): List<Car>{
        return carDataSource.carsFilteredByBatteryPercentage(batteryPercentage)
    }
}