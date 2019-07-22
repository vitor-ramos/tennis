package dev.vitorramos.tennis

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import dev.vitorramos.tennis.view.StartMatchActivity
import org.junit.Rule
import org.junit.Test

class StartMatchTest {
    @get:Rule
    val startMatchActivity = ActivityTestRule(StartMatchActivity::class.java)

    @Test
    fun gameNoNames() {
        onView(withId(R.id.et_start_match_confirm)).perform(click())

        onView(withId(R.id.match_host_name)).check(matches(withText(YOU)))
        onView(withId(R.id.match_guest_name)).check(matches(withText(GUEST)))
    }

    @Test
    fun gameWithNames() {
        onView(withId(R.id.et_start_match_host_name)).perform(typeText(HOST_NAME), closeSoftKeyboard())
        onView(withId(R.id.et_start_match_guest_name)).perform(typeText(GUEST_NAME), closeSoftKeyboard())

        onView(withId(R.id.et_start_match_confirm)).perform(click())

        onView(withId(R.id.match_host_name)).check(matches(withText(HOST_NAME)))
        onView(withId(R.id.match_guest_name)).check(matches(withText(GUEST_NAME)))
    }

    @Test
    fun gameScore() {
        onView(withId(R.id.et_start_match_confirm)).perform(click())

        onView(withId(R.id.match_host_games)).check(matches(withText(ZERO)))
        onView(withId(R.id.match_guest_games)).check(matches(withText(ZERO)))

        onView(withId(R.id.match_host_sets)).check(matches(withText(ZERO)))
        onView(withId(R.id.match_guest_sets)).check(matches(withText(ZERO)))

        onView(withId(R.id.match_host_points)).check(matches(withText(ZERO)))
        onView(withId(R.id.match_guest_points)).check(matches(withText(ZERO)))
    }

    companion object {
        const val YOU = "Você"
        const val GUEST = "Convidado"
        const val HOST_NAME = "Vitor"
        const val GUEST_NAME = "Isadora"
        const val ZERO = "0"
    }
}
