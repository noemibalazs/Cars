package com.noemi.android.core.interactors

import com.noemi.android.core.datasource.CarRepository
import com.noemi.android.core.domain.Car

class ShowsCars(private val carRepository: CarRepository) {

    suspend operator fun invoke(): List<Car> {
        return carRepository.showCars()
    }
}