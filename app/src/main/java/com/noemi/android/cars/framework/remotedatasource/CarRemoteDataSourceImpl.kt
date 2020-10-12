package com.noemi.android.cars.framework.remotedatasource

import androidx.lifecycle.*
import com.noemi.android.cars.framework.network.*
import com.noemi.android.cars.presentation.helper.*
import com.noemi.android.core.domain.Car
import com.orhanobut.logger.Logger
import kotlinx.coroutines.*
import org.koin.core.logger.KOIN_TAG
import retrofit2.*

class CarRemoteDataSourceImpl(
    private val mapper: Mapper,
    private val carServiceAPI: CarServiceAPI
) : CarRemoteDataSource {

    private val failureError = SingleLiveData<Any>()
    private val loading = SingleLiveData<Boolean>()

    override fun getRemoteCarsList(): LiveData<List<Car>> {
        val mutableCarList = MutableLiveData<List<Car>>()
        loading.value = true
        GlobalScope.launch(Dispatchers.IO) {
            val response = carServiceAPI.getListOfCars()
            withContext(Dispatchers.Main) {
                try {
                    response.enqueue(object : Callback<List<CarResponse>> {
                        override fun onFailure(call: Call<List<CarResponse>>, t: Throwable) {
                            Logger.e(KOIN_TAG, "onFailure error message: ${t.message}")
                            failureError.call()
                            loading.value = false
                        }

                        override fun onResponse(
                            call: Call<List<CarResponse>>,
                            response: Response<List<CarResponse>>
                        ) {
                            loading.value = false
                            if (!response.isSuccessful) {
                                Logger.e(KOIN_TAG, "onResponse error code: ${response.code()}")
                                failureError.call()
                            }

                            if (response.isSuccessful) {
                                Logger.d(KOIN_TAG, "onResponse is successful!")
                                val result = response.body()
                                result?.let { carResponseList ->
                                    val carList = carResponseList.map { carResponse -> mapper.mapCarResponse2Car(carResponse) }
                                    mutableCarList.value = carList
                                }
                            }
                        }
                    })

                } catch (e: Exception) {
                    Logger.e(KOIN_TAG, "Error exception message: ${e.message}")
                }
            }
        }

        return mutableCarList
    }

    override val mutableFailureError: SingleLiveData<Any>
        get() = failureError

    override val showLoading: SingleLiveData<Boolean>
        get() = loading
}