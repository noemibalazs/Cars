package com.noemi.android.cars.presentation.cars

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.noemi.android.cars.framework.action.InterActors
import com.noemi.android.cars.framework.remotedatasource.CarRemoteDataSource
import com.noemi.android.cars.presentation.helper.SingleLiveData
import com.noemi.android.core.domain.Car
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.logger.KOIN_TAG

class CarViewModel(
    val carRemoteDataSource: CarRemoteDataSource,
    private val interActors: InterActors
) : ViewModel() {

    val failureError = carRemoteDataSource.mutableFailureError
    val loading = carRemoteDataSource.showLoading
    val carList = carRemoteDataSource.getRemoteCarsList()

    val mutablePlateNumber = ObservableField("")
    val mutableFilteredCars = SingleLiveData<List<Car>>()


    fun addCars2DB(cars: List<Car>) {
        Logger.d(KOIN_TAG, "addCars2DB")
        GlobalScope.launch(Dispatchers.IO) {
            interActors.addCars.invoke(cars)
        }
    }


    fun onFilterByPlateDoneClicked(){
        Logger.d(KOIN_TAG, "onFilterByPlateDoneClicked")
        loadFilteredCarsByPlate()
    }

    private fun loadFilteredCarsByPlate(){
        Logger.d(KOIN_TAG, "loadFilteredCarsByPlate")
        GlobalScope.launch(Dispatchers.IO){
            val plateFiltered = interActors.filteredByPlateNumber.invoke(mutablePlateNumber.get() ?: return@launch)
            withContext(Dispatchers.Main){
                try {
                    mutableFilteredCars.value = plateFiltered
                    mutablePlateNumber.set("")
                }catch (e: Exception){
                    Log.e(KOIN_TAG, "loadFilteredCarsByPlate throws error: ${e.message}")
                }
            }
        }
    }
}