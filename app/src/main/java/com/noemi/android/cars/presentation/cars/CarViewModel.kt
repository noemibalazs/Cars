package com.noemi.android.cars.presentation.cars

import androidx.lifecycle.ViewModel
import com.noemi.android.cars.framework.action.InterActors
import com.noemi.android.cars.framework.remotedatasource.CarRemoteDataSource
import com.noemi.android.core.domain.Car
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.logger.KOIN_TAG

class CarViewModel(
    val carRemoteDataSource: CarRemoteDataSource,
    private val interActors: InterActors
) : ViewModel() {

    val failureError = carRemoteDataSource.mutableFailureError
    val loading = carRemoteDataSource.showLoading
    val carList = carRemoteDataSource.getRemoteCarsList()

    fun addCars2DB(cars: List<Car>) {
        Logger.d(KOIN_TAG, "addCars2DB")
        GlobalScope.launch(Dispatchers.IO) {
            interActors.addCars.invoke(cars)
        }
    }
}