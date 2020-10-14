package com.noemi.android.cars.presentation.cars

import android.location.Location
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.noemi.android.cars.framework.action.InterActors
import com.noemi.android.cars.framework.remotedatasource.CarRemoteDataSource
import com.noemi.android.cars.presentation.helper.DataManager
import com.noemi.android.cars.presentation.helper.SingleLiveData
import com.noemi.android.core.domain.Car
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.logger.KOIN_TAG
import java.util.*

class CarViewModel(
    val carRemoteDataSource: CarRemoteDataSource,
    private val interActors: InterActors,
    private val dataManager: DataManager
) : ViewModel() {

    val failureError = carRemoteDataSource.mutableFailureError
    val loading = carRemoteDataSource.showLoading
    val carList = carRemoteDataSource.getRemoteCarsList()

    val mutablePlateNumber = ObservableField("")
    val mutablePlateNumberFilteredCars = SingleLiveData<List<Car>>()

    val mutableBatteryPercentage = ObservableField("")
    val mutableBatteryFilteredCars = SingleLiveData<List<Car>>()

    val mutableCarsFromDB = SingleLiveData<List<Car>>()
    val mutableCarsBasedOnDistance = SingleLiveData<List<Car>>()

    fun addCars2DB(cars: List<Car>) {
        Logger.d(KOIN_TAG, "addCars2DB")
        GlobalScope.launch(Dispatchers.IO) {
            interActors.addCars.invoke(cars)
        }
    }

    fun onFilterByPlateDoneClicked() {
        Logger.d(KOIN_TAG, "onFilterByPlateDoneClicked")
        loadFilteredCarsByPlate()
    }

    private fun loadFilteredCarsByPlate() {
        Logger.d(KOIN_TAG, "loadFilteredCarsByPlate")
        GlobalScope.launch(Dispatchers.IO) {
            val plateFiltered =
                interActors.filteredByPlateNumber.invoke(mutablePlateNumber.get() ?: return@launch)
            withContext(Dispatchers.Main) {
                try {
                    mutablePlateNumberFilteredCars.value = plateFiltered
                    mutablePlateNumber.set("")
                } catch (e: Exception) {
                    Log.e(KOIN_TAG, "loadFilteredCarsByPlate throws error: ${e.message}")
                }
            }
        }
    }

    fun onFilterByBatteryPercentageClicked() {
        Logger.d(KOIN_TAG, "onFilterByBatteryPercentage")
        loadFilteredCarsByGreaterBatteryPercentage()
    }

    private fun loadFilteredCarsByGreaterBatteryPercentage() {
        Logger.d(KOIN_TAG, "loadFilteredCarsByGreaterBatteryPercentage")
        GlobalScope.launch(Dispatchers.IO) {
            val percentage = mutableBatteryPercentage.get()?.toIntOrNull()
            val percentageList = interActors.filteredByBatteryPercentage.invoke(
                percentage ?: 0
            )
            withContext(Dispatchers.Main) {
                try {
                    mutableBatteryFilteredCars.value = percentageList
                    mutableBatteryPercentage.set("")
                } catch (e: Exception) {
                    Log.e(
                        KOIN_TAG,
                        "loadFilteredCarsByGreaterBatteryPercentage throws error: ${e.message}"
                    )
                }
            }
        }
    }

    fun showsCarListFromDB() {
        Logger.d(KOIN_TAG, "showsCarListFromDB")
        GlobalScope.launch(Dispatchers.IO) {
            val cars = interActors.showsCars.invoke()
            withContext(Dispatchers.Main) {
                try {
                    mutableCarsFromDB.value = cars
                } catch (e: Exception) {
                    Logger.e(KOIN_TAG, "Exception was thrown!")
                }
            }
        }
    }

    fun carListFromDB(cars: List<Car>) {
        val sorted: SortedMap<Double, Car> = sortedMapOf()
        val userLocation = loadUserLocation()
        val carPosition = Location("Car position")
        cars.forEach { car ->
            carPosition.latitude = car.location.latitude
            carPosition.longitude = car.location.longitude
            val distance = userLocation.distanceTo(carPosition)
            val km = meter2KM(distance)
            sorted[km] = car
        }

        val carsList = sorted.values
        mutableCarsBasedOnDistance.value = carsList.toList()
    }

    private fun loadUserLocation(): Location {
        val userLocation = Location("User location")
        userLocation.latitude = dataManager.getLastKnownLatitude()
        userLocation.longitude = dataManager.getLastKnownLongitude()
        return userLocation
    }

    private fun meter2KM(distance: Float): Double {
        val kmUnformatted = (distance / 1000).toDouble()
        return String.format("%.2f", kmUnformatted).toDouble()
    }
}