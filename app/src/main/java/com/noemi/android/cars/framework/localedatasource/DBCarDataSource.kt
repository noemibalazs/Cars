package com.noemi.android.cars.framework.localedatasource

import com.noemi.android.cars.framework.db.CarDataBase
import com.noemi.android.cars.framework.db.CarEntity
import com.noemi.android.cars.presentation.helper.Mapper
import com.noemi.android.core.datasource.CarDataSource
import com.noemi.android.core.domain.Car

class DBCarDataSource(
    private val carDataBase: CarDataBase,
    private val mapper: Mapper
) : CarDataSource {

    override suspend fun addCarsToDB(cars: List<Car>) {
        val mutableList = mutableListOf<CarEntity>()
        cars.forEach { car -> mutableList.add(mapper.mapCar2CarEntity(car)) }
        carDataBase.carDAO().addCars2DB(mutableList.toList())
    }

    override suspend fun showsCarsFromDB(): List<Car> {
        val entityList = carDataBase.carDAO().showCarList()
        return entityList.map { entity -> mapper.mapCarEntity2Car(entity) }
    }

    override suspend fun carsFilteredByPlateNumber(plate: String): List<Car> {
        val entityList = carDataBase.carDAO().showFilteredCarsByPlateNumber(plate)
        return entityList.map { entity -> mapper.mapCarEntity2Car(entity) }
    }

    override suspend fun carsFilteredByBatteryPercentage(battery: Int): List<Car> {
        val entityList = carDataBase.carDAO().showFilteredCarsByRemainingBattery(battery)
        return entityList.map { entity -> mapper.mapCarEntity2Car(entity) }
    }
}