package com.noemi.android.core.datasource

import com.noemi.android.core.domain.Car

interface CarDataSource {

    suspend fun addCarsToDB(cars: List<Car>)

    suspend fun showsCarsFromDB(): List<Car>
}