package it.carlo.skydevtest

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import it.carlo.skydevtest.view.DetailActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class DetailActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(DetailActivity::class.java)

    @Test
    fun testViewPagerVisibility() {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.viewPager), ViewMatchers.isDisplayed()))
    }

    @Test
    fun testViewPagerSwipeDown() {
        val viewPager =  Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.viewPager), ViewMatchers.isDisplayed()))
        //first page (photo fullscreen)
        viewPager.perform(ViewActions.swipeDown())
        //second page (photo description)
        viewPager.perform(ViewActions.swipeDown())
        //thirg page (webview)
        viewPager.perform(ViewActions.swipeDown())
    }

    @Test
    fun testViewPagerSwipeUp() {
        val viewPager =  Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.viewPager), ViewMatchers.isDisplayed()))
        //come back to description
        viewPager.perform(ViewActions.swipeUp())
        //come back to photo full screen
        viewPager.perform(ViewActions.swipeDown())
    }




}
