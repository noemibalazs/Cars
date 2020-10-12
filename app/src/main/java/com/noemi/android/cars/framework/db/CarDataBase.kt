package com.noemi.android.cars.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.noemi.android.cars.presentation.util.CAR_DB

@Database(entities = [CarEntity::class], version = 1, exportSchema = false)
@TypeConverters(CarLocationConverter::class, CarModelConverter::class)
abstract class CarDataBase : RoomDatabase() {

    abstract fun carDAO(): CarDAO

    companion object {
        fun getCarDB(context: Context): CarDataBase {
            return Room.databaseBuilder(context, CarDataBase::class.java, CAR_DB).build()
        }
    }
}