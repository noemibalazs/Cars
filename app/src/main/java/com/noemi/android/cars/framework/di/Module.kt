package com.noemi.android.cars.framework.di

import com.noemi.android.cars.framework.action.InterActors
import com.noemi.android.cars.framework.localedatasource.DBCarDataSource
import com.noemi.android.cars.framework.db.CarDataBase
import com.noemi.android.cars.framework.network.CarServiceAPI
import com.noemi.android.cars.framework.remotedatasource.CarRemoteDataSource
import com.noemi.android.cars.framework.remotedatasource.CarRemoteDataSourceImpl
import com.noemi.android.cars.presentation.cars.CarViewModel
import com.noemi.android.cars.presentation.helper.Mapper
import com.noemi.android.core.datasource.CarDataSource
import com.noemi.android.core.datasource.CarRepository
import com.noemi.android.core.interactors.AddCars
import com.noemi.android.core.interactors.ShowsCars
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mapperModule = module {
    factory { Mapper() }
}

val carDataBaseModule = module {
    single { CarDataBase.getCarDB(androidApplication().applicationContext) }
}

val carNetworkModule = module {
    single { CarServiceAPI.getCarService() }
}

val dbCarDataSourceModule = module {
    single<CarDataSource> {
        DBCarDataSource(
            carDataBase = get(),
            mapper = get()
        )
    }
}

val carRepositoryModule = module {
    single { CarRepository(carDataSource = get()) }
}

val actorsModule = module {
    single { AddCars(carRepository = get()) }
    single { ShowsCars(carRepository = get()) }
}

val interActorsModule = module {
    single {
        InterActors(
            addCars = get(),
            showsCars = get()
        )
    }
}

val carViewModelModule = module {
    single<CarRemoteDataSource> {
        CarRemoteDataSourceImpl(
            mapper = get(),
            carServiceAPI = get()
        )
    }
    viewModel {
        CarViewModel(
            carRemoteDataSource = get(),
            interActors = get()
        )
    }
}