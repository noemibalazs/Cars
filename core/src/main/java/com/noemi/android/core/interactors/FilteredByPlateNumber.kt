package com.noemi.android.core.interactors

import com.noemi.android.core.datasource.CarRepository
import com.noemi.android.core.domain.Car

class FilteredByPlateNumber(private val carRepository: CarRepository) {

    suspend operator fun invoke(plateNumber: String): List<Car>{
        return carRepository.filteredCarsByPlateNumber(plateNumber)
    }
}