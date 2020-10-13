package com.noemi.android.core.interactors

import com.noemi.android.core.datasource.CarRepository
import com.noemi.android.core.domain.Car

class FilteredByBatteryPercentage(private val carRepository: CarRepository) {

    suspend operator fun invoke(battery: Int): List<Car> {
        return carRepository.filteredCarsByBatteryPercentage(battery)
    }
}