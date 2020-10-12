package com.noemi.android.core.datasource

import com.noemi.android.core.domain.Car

class CarRepository(private val carDataSource: CarDataSource) {

    suspend fun addCars2DB(cars: List<Car>) {
        carDataSource.addCarsToDB(cars)
    }

    suspend fun showCars(): List<Car> {
        return carDataSource.showsCarsFromDB()
    }
}