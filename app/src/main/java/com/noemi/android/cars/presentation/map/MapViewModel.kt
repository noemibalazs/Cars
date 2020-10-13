package com.noemi.android.cars.presentation.map

import androidx.lifecycle.ViewModel
import com.noemi.android.cars.framework.action.InterActors
import com.noemi.android.cars.presentation.helper.SingleLiveData
import com.noemi.android.core.domain.Car
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.logger.KOIN_TAG

class MapViewModel(
    val interActors: InterActors
) : ViewModel() {

    val mutableCarList = SingleLiveData<List<Car>>()

    init {
        showCarList()
    }

    private fun showCarList() {
        Logger.d(KOIN_TAG, "showCarList")
        GlobalScope.launch(Dispatchers.IO) {
            val cars = interActors.showsCars.invoke()
            withContext(Dispatchers.Main) {
                try {
                    mutableCarList.value = cars
                } catch (e: Exception) {
                    Logger.e(KOIN_TAG, "Exception was thrown!")
                }
            }
        }
    }
}