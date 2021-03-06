package com.noemi.android.cars.framework.di

import org.koin.core.module.Module

class KoinInjection {

    companion object {
        fun injectModules(): MutableList<Module> {
            fun getMapperModule() = listOf(mapperModule)
            fun getCarDataBaseModule() = listOf(carDataBaseModule)
            fun getCarNetworkModule() = listOf(carNetworkModule)
            fun getDBCarDataSourceModule() = listOf(dbCarDataSourceModule)
            fun getCarRepositoryModule() = listOf(carRepositoryModule)
            fun getActorsModule() = listOf(actorsModule)
            fun getInterActorsModule() = listOf(interActorsModule)
            fun getCarViewModelModule() = listOf(carViewModelModule)
            fun getMapViewModelModule() = listOf(mapViewModelModule)
            fun getDataManagerModule() = listOf(dataManagerModule)

            return mutableListOf<Module>().apply {
                addAll(getMapperModule())
                addAll(getCarDataBaseModule())
                addAll(getCarNetworkModule())
                addAll(getDBCarDataSourceModule())
                addAll(getCarRepositoryModule())
                addAll(getActorsModule())
                addAll(getInterActorsModule())
                addAll(getCarViewModelModule())
                addAll(getMapViewModelModule())
                addAll(getDataManagerModule())
            }
        }
    }

}