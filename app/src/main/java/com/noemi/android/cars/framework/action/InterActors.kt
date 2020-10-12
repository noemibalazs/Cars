package com.noemi.android.cars.framework.action

import com.noemi.android.core.interactors.AddCars
import com.noemi.android.core.interactors.ShowsCars

data class InterActors(
    val showsCars: ShowsCars,
    val addCars: AddCars
) {
    override fun toString(): String {
        return "InterActors: showsCars=$showsCars, addCars=$addCars"
    }
}