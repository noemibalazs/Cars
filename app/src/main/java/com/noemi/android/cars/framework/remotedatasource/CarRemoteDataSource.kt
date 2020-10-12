package com.noemi.android.cars.framework.remotedatasource

import androidx.lifecycle.LiveData
import com.noemi.android.cars.presentation.helper.SingleLiveData
import com.noemi.android.core.domain.Car

interface CarRemoteDataSource {

    fun getRemoteCarsList(): LiveData<List<Car>>

    val mutableFailureError: SingleLiveData<Any>

    val showLoading : SingleLiveData<Boolean>
}