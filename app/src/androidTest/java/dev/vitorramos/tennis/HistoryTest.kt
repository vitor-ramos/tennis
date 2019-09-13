package dev.vitorramos.tennis

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import dev.vitorramos.tennis.view.HistoryActivity
import org.junit.Rule
import org.junit.Test

class HistoryTest {
    @get:Rule
    val startMatchActivity = ActivityTestRule(HistoryActivity::class.java)

    @Test
    fun startMatch() {
        onView(withText(getString(R.string.start_match))).perform(click())

        onView(withHint(getString(R.string.your_name)))
            .check(matches(isDisplayed()))
        onView(withHint(getString(R.string.guest)))
            .check(matches(isDisplayed()))
        onView(withHint(getString(R.string.games)))
            .check(matches(isDisplayed()))
        onView(withHint(getString(R.string.sets)))
            .check(matches(isDisplayed()))
        onView(withText(getString(R.string.start_match)))
            .check(matches(isDisplayed()))
    }

    private fun getString(@IdRes id: Int): String {
        return InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(id)
    }
}
