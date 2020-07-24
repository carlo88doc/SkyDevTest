package it.carlo.skydevtest

import android.util.Log
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import it.carlo.skydevtest.model.DiskRepository
import it.carlo.skydevtest.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class SettingsFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test fun testAdultSwitch() {
        val bottomNavigationView = Espresso.onView(ViewMatchers.withId(R.id.bottomNavigationView))
        val adultSwitch = Espresso.onView(ViewMatchers.withId(R.id.switchSettingsOver18))

        bottomNavigationView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.navigation_settings)).perform(ViewActions.click())
        adultSwitch.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        adultSwitch.perform(ViewActions.click())
    }
}
