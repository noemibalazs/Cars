package com.noemi.android.cars.framework.action

import com.noemi.android.core.interactors.AddCars
import com.noemi.android.core.interactors.FilteredByBatteryPercentage
import com.noemi.android.core.interactors.FilteredByPlateNumber
import com.noemi.android.core.interactors.ShowsCars

data class InterActors(
    val showsCars: ShowsCars,
    val addCars: AddCars,
    val filteredByPlateNumber: FilteredByPlateNumber,
    val filteredByBatteryPercentage: FilteredByBatteryPercentage
) {
    override fun toString(): String {
        return "InterActors: showsCars=$showsCars, addCars=$addCars, filteredByPlateNumber=$filteredByPlateNumber, filteredByBatteryPercentage=$filteredByBatteryPercentage"
    }

}