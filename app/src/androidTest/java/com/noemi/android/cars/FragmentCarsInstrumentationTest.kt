package com.noemi.android.cars

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.noemi.android.cars.framework.adapter.CarAdapter
import com.noemi.android.cars.framework.adapter.CarVH
import com.noemi.android.cars.presentation.cars.CarViewModel
import com.noemi.android.cars.presentation.landing.MainActivity
import com.noemi.android.core.domain.Car
import com.noemi.android.core.domain.Location
import com.noemi.android.core.domain.Model
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class FragmentCarsInstrumentationTest {

    @get: Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var viewModel: CarViewModel

    @Before
    fun setUp() {
        viewModel = mockk<CarViewModel>()
    }

    @Test
    fun testCar(){
        val location = Location(33.66, 66.99, "Haven 77")
        val model = Model(12, "Jaguar XF", "https://s3-eu-west-1.amazonaws.com/rideshareuploads/uploads/f5b68583-a28c-4016-9590-9a78423621d2.jpeg")
        val jaguar = Car(12, "JaguarXF", location, model, 69, 99)
        val carList = listOf<Car>(jaguar)
        val adapter = CarAdapter(viewModel)
        adapter.submitList(carList)

        activityTestRule.activity.runOnUiThread {
            activityTestRule.activity.findViewById<RecyclerView>(R.id.rv_cars).adapter =
                adapter
        }

        Espresso.onView(withId(R.id.rv_cars))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_cars)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CarVH>(
                0,
                ViewActions.click()
            )
        )
    }
}