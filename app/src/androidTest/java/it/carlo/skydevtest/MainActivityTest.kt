package it.carlo.skydevtest

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import it.carlo.skydevtest.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test fun testBottomNavigationVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.bottomNavigationView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test fun testBottomNavigationHomeClick() {
        testBottomNavigation(R.id.navigation_home)
    }

    @Test fun testBottomNavigationPhotosClick() {
        testBottomNavigation(R.id.navigation_photos)
    }

    @Test fun testBottomNavigationSettingsClick() {
        testBottomNavigation(R.id.navigation_settings)
    }

    private fun testBottomNavigation(menuItemId:Int){
        Espresso.onView(ViewMatchers.withId(menuItemId)).perform(ViewActions.click())
    }

}
