package com.noemi.android.cars

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.core.AllOf.allOf
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.noemi.android.cars.presentation.landing.MainActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityInstrumentationTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppContext() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals("com.noemi.android.cars.test", context.packageName)
    }

    @Test
    fun testBottomNavigationIsVisible() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
    }

    @Test
    fun testOpenMapFragment() {
        val context = rule.activity.applicationContext
        onView(
            allOf(
                withText(context.getString(R.string.txt_maps)),
                isDescendantOfA(withId(R.id.bottom_navigation)),
                isDisplayed()
            )
        )
            .perform(click());
    }
}