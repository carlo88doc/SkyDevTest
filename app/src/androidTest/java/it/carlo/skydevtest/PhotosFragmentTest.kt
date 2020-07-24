package it.carlo.skydevtest

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import it.carlo.skydevtest.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class PhotosFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)


    @Test fun testBrowsingPhotos() {
        val bottomNavigationView = Espresso.onView(ViewMatchers.withId(R.id.bottomNavigationView))
        val searchEditText = Espresso.onView(ViewMatchers.withId(R.id.photosSearchEditText))
        val buttonSearch =  Espresso.onView(ViewMatchers.withId(R.id.photosSearchImageButton))

        bottomNavigationView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.navigation_photos)).perform(ViewActions.click())

        searchEditText.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        searchEditText.perform(ViewActions.typeText("photos"))

        buttonSearch.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        buttonSearch.perform(ViewActions.click())

        Thread.sleep(5000)

        val recyclerView = Espresso.onView(ViewMatchers.withId(R.id.photosRecyclerView))
        recyclerView.perform(ViewActions.swipeDown())
        recyclerView.perform(ViewActions.swipeUp())
        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
    }

}
