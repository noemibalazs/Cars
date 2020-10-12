package com.noemi.android.cars.framework.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CarModelConverter {

    @TypeConverter
    fun carModel2String(carModel: CarModel): String {
        return Gson().toJson(carModel)
    }

    @TypeConverter
    fun string2CarModel(json: String): CarModel {
        val type = object : TypeToken<CarModel>() {}.type
        return Gson().fromJson(json, type)
    }
}