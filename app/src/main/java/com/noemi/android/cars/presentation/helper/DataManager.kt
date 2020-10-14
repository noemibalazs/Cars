package com.noemi.android.cars.presentation.helper

import android.content.Context
import com.noemi.android.cars.presentation.util.LAST_KNOW_LATITUDE
import com.noemi.android.cars.presentation.util.LAST_KNOW_LONGITUDE

class DataManager(val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("My preferences", Context.MODE_PRIVATE)

    fun setLastKnownLatitude(latitude: Double) {
        sharedPreferences.edit()
            .putLong(LAST_KNOW_LATITUDE, java.lang.Double.doubleToRawLongBits(latitude)).apply()
    }

    fun getLastKnownLatitude(): Double {
        return java.lang.Double.longBitsToDouble(sharedPreferences.getLong(LAST_KNOW_LATITUDE, 0))
    }

    fun setLastKnowLongitude(longitude: Double) {
        sharedPreferences.edit()
            .putLong(LAST_KNOW_LONGITUDE, java.lang.Double.doubleToRawLongBits(longitude)).apply()
    }

    fun getLastKnownLongitude(): Double {
        return java.lang.Double.longBitsToDouble(sharedPreferences.getLong(LAST_KNOW_LONGITUDE, 0))
    }
}