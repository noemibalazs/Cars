package com.noemi.android.cars

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
class ModelUnitTest {

    private val model = Model(12, "Jaguar XF", "")

    @Test
    fun testModelShouldPass(){
        val expectedModel = Model(12, "Jaguar XF", "")
        assertEquals(expectedModel, model)
    }

    @Test
    fun testModelShouldFailed(){
        val expectedModel = Model(12, "Jaguar XF", "..")
        assertNotEquals(expectedModel, model)
    }

    @After
    fun tearDown(){
        stopKoin()
    }
}