package com.noemi.android.cars

import com.noemi.android.core.domain.Location
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.koin.core.context.stopKoin

@RunWith(RobolectricTestRunner::class)
@org.robolectric.annotation.Config(manifest= Config.NONE)
class LocationUnitTest {

    private val location = Location(33.66, 66.99, "Haven 77")

    @Test
    fun testLocationShouldPass(){
        val expectedLocation = Location(33.66, 66.99, "Haven 77")
        assertEquals(expectedLocation, location)
    }

    @Test
    fun testLocationShouldFailed(){
        val expectedLocation = Location(33.66, 66.99, "Haven 99")
        assertNotEquals(expectedLocation, location)
    }

    @After
    fun tearDown(){
        stopKoin()
    }
}