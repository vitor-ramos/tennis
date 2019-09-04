package dev.vitorramos.tennis

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import dev.vitorramos.tennis.view.StartMatchActivity
import org.junit.Rule
import org.junit.Test

class StartMatchTest {
    @get:Rule
    val startMatchActivity = ActivityTestRule(StartMatchActivity::class.java)

    @Test
    fun gameNoNames() {
        onView(withText(getString(R.string.start_match)))
            .perform(click())

        onView(withText(getString(R.string.you)))
            .check(matches(isDisplayed()))
        onView(withText(getString(R.string.guest)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun gameWithNames() {
        onView(withHint(getString(R.string.your_name)))
            .perform(typeText(getString(R.string.placeholder_host)), closeSoftKeyboard())
        onView(withHint(getString(R.string.guest)))
            .perform(typeText(getString(R.string.placeholder_guest)), closeSoftKeyboard())

        onView(withText(getString(R.string.start_match)))
            .perform(click())

        onView(withText(getString(R.string.placeholder_host)))
            .check(matches(isDisplayed()))
        onView(withText(getString(R.string.placeholder_guest)))
            .check(matches(isDisplayed()))
    }

    private fun getString(@IdRes id: Int): String {
        return InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(id)
    }
}
