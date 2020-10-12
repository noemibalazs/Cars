package com.noemi.android.cars.presentation.helper

import com.noemi.android.cars.framework.db.*
import com.noemi.android.cars.framework.network.CarResponse
import com.noemi.android.core.domain.*

class Mapper {

    fun mapCarResponse2CarEntity(carResponse: CarResponse): CarEntity {
        val location = carResponse.location
        val carLocation = CarLocation(
            longitude = location.longitude,
            latitude = location.latitude,
            address = location.address
        )
        val model = carResponse.model
        val carModel = CarModel(id = model.id, title = model.title, photoUrl = model.photoUrl)
        return CarEntity(
            id = carResponse.id,
            plateNumber = carResponse.plateNumber,
            location = carLocation,
            model = carModel,
            batteryEstimatedDistance = carResponse.batteryEstimatedDistance,
            batteryPercentage = carResponse.batteryPercentage
        )
    }

    fun mapCarResponse2Car(carResponse: CarResponse): Car {
        val carLocation = carResponse.location
        val location = Location(
            longitude = carLocation.longitude,
            latitude = carLocation.latitude,
            address = carLocation.address
        )
        val carModel = carResponse.model
        val model = Model(id = carModel.id, title = carModel.title, photoUrl = carModel.photoUrl)
        return Car(
            id = carResponse.id,
            plateNumber = carResponse.plateNumber,
            location = location,
            model = model,
            batteryPercentage = carResponse.batteryPercentage,
            batteryEstimatedDistance = carResponse.batteryEstimatedDistance
        )
    }

    fun mapCarEntity2Car(entity: CarEntity): Car {
        val carLocation = entity.location
        val location = Location(
            longitude = carLocation.longitude,
            latitude = carLocation.latitude,
            address = carLocation.address
        )
        val carModel = entity.model
        val model = Model(id = carModel.id, title = carModel.title, photoUrl = carModel.photoUrl)
        return Car(
            id = entity.id,
            plateNumber = entity.plateNumber,
            location = location,
            model = model,
            batteryPercentage = entity.batteryPercentage,
            batteryEstimatedDistance = entity.batteryEstimatedDistance
        )
    }

    fun mapCar2CarEntity(car: Car): CarEntity {
        val location = car.location
        val carLocation = CarLocation(
            longitude = location.longitude,
            latitude = location.latitude,
            address = location.address
        )
        val model = car.model
        val carModel = CarModel(id = model.id, photoUrl = model.photoUrl, title = model.title)
        return CarEntity(
            id = car.id,
            plateNumber = car.plateNumber,
            location = carLocation,
            model = carModel,
            batteryEstimatedDistance = car.batteryEstimatedDistance,
            batteryPercentage = car.batteryPercentage
        )
    }
}