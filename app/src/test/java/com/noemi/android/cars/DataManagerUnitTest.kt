package com.noemi.android.cars

import com.noemi.android.cars.presentation.helper.DataManager
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@org.robolectric.annotation.Config(manifest= Config.NONE)
class DataManagerUnitTest {

    private var dataManager: DataManager?= null
    private val longitude = 66.99
    private val latitude = 33.12

    @Before
    fun createDataManager() {
        dataManager = DataManager(RuntimeEnvironment.application.applicationContext)
    }

    @Test
    fun lngCoordinateShouldPassed(){
        dataManager?.setLastKnowLongitude(longitude)
        val lng = dataManager?.getLastKnownLongitude()
        assertEquals(longitude, lng)
    }

    @Test
    fun lngCoordinateShouldFailed(){
        dataManager?.setLastKnowLongitude(longitude)
        val lng = dataManager?.getLastKnownLongitude()
        assertNotEquals(12.12, lng)
    }

    @Test
    fun latCoordinateShouldPassed(){
        dataManager?.setLastKnownLatitude(latitude)
        val lat = dataManager?.getLastKnownLatitude()
        assertEquals(latitude, lat)
    }

    @Test
    fun latCoordinateShouldFailed(){
        dataManager?.setLastKnownLatitude(latitude)
        val lat = dataManager?.getLastKnownLatitude()
        assertNotEquals(33.33, lat)
    }

    @After
    fun tearDown(){
        stopKoin()
    }

}