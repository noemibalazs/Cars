package com.noemi.android.cars.framework.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CarLocationConverter {

    @TypeConverter
    fun carLocation2String(carLocation: CarLocation): String {
        return Gson().toJson(carLocation)
    }

    @TypeConverter
    fun string2CarLocation(json: String): CarLocation {
        val type = object : TypeToken<CarLocation>() {}.type
        return Gson().fromJson(json, type)
    }
}