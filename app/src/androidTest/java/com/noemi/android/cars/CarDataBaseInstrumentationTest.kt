package com.noemi.android.cars

import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.noemi.android.cars.framework.db.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CarDataBaseInstrumentationTest {

    private lateinit var carDataBase: CarDataBase
    private lateinit var carDAO: CarDAO

    @Before
    fun setUp() {
        carDataBase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            CarDataBase::class.java
        ).build()
        carDAO = carDataBase.carDAO()
    }

    @Test
    @Throws(Exception::class)
    fun addCarsToDB(){
        val carLocation = CarLocation(33.99, 66.09, "Haven 99")
        val carModel = CarModel(9, "Jaguar", "XF")
        val carEntity = CarEntity(12, "JAGUAR12", carLocation, carModel, 77, 99)
        GlobalScope.launch(Dispatchers.IO){
            val id = carDAO.addCars2DB(listOf(carEntity))
            Assert.assertNotNull(id)
        }
    }
}