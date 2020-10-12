package com.noemi.android.core.interactors

import com.noemi.android.core.datasource.CarRepository
import com.noemi.android.core.domain.Car

class AddCars(private val carRepository: CarRepository) {

    suspend operator fun invoke(cars: List<Car>) {
        carRepository.addCars2DB(cars)
    }
}