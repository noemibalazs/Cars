package com.noemi.android.cars

import com.noemi.android.core.domain.Car
import com.noemi.android.core.domain.Location
import com.noemi.android.core.domain.Model
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@org.robolectric.annotation.Config(manifest = Config.NONE)
class CarUnitTest {

    private val location = Location(33.66, 66.99, "Haven 77")
    private val model = Model(12, "Jaguar XF", "")
    private val jaguar = Car(12, "JaguarXF", location, model, 69, 99)

    @Test
    fun testCarShouldPass(){
        val expectedCar = Car(12, "JaguarXF", location, model, 69, 99)
        assertEquals(expectedCar, jaguar)
    }

    @Test
    fun testCarShouldFailed(){
        val expectedCar = Car(9, "JaguarXF", location, model, 69, 99)
        assertNotEquals(expectedCar, jaguar)
    }

    @After
    fun tearDown(){
        stopKoin()
    }
}