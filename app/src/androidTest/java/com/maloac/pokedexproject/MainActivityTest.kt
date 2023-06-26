package com.maloac.pokedexproject

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Test
    fun test_isActivityInView () {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.clMain)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isPokedexListInView() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.clMain)).check(matches(isDisplayed()))
        onView(withId(R.id.rvPokedex)).check(matches(isDisplayed()))
    }

    @Test
    fun test_ListTap() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.clMain)).check(matches(isDisplayed()))
        onView(withId(R.id.rvPokedex)).perform(click())
    }

    @Test
    fun test_checkDetailActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.clMain)).check(matches(isDisplayed()))
        onView(withId(R.id.rvPokedex)).check(matches(isDisplayed()))
        onView(withId(R.id.rvPokedex)).perform(click())

        onView(withId(R.id.clPokemonDetails)).check(matches(isDisplayed()))
        onView(withId(R.id.tvPokemonName)).check(matches(isDisplayed()))
    }

    @Test
    fun test_fromDetailActivityGoToMain() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.clMain)).check(matches(isDisplayed()))
        onView(withId(R.id.rvPokedex)).check(matches(isDisplayed()))
        onView(withId(R.id.rvPokedex)).perform(click())

        onView(withId(R.id.clPokemonDetails)).check(matches(isDisplayed()))
        onView(withId(R.id.tvPokemonName)).check(matches(isDisplayed()))
        onView(withId(R.id.clPokemonDetails)).perform(ViewActions.pressBack())

        onView(withId(R.id.clMain)).check(matches(isDisplayed()))
        onView(withId(R.id.rvPokedex)).check(matches(isDisplayed()))
    }
}