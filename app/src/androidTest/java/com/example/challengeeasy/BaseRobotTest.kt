package com.example.challengeeasy

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not


open class BaseRobotTest {

    protected fun setEditText(@IdRes id: Int, textType: String) {
        onView(withId(id)).perform(replaceText(textType), closeSoftKeyboard())
    }

    protected fun clickButton(@IdRes id: Int) {
        onView(withId(id)).perform(click())
    }

    protected fun checkToast(text: String, activityTestRule: ActivityTestRule<Activity>) {
        onView(withText(text))
            .inRoot(withDecorView(not(`is`(activityTestRule.activity.window.decorView))))
            .check(matches(isDisplayed()))

    }

    protected fun checkIntent(activity: Activity, name: String, value: Parcelable) {
        val intent = Intent()
        intent.putExtra(name, value)
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, intent)
        intending(toPackage(activity::getPackageName::class.java.name)).respondWith(result)
    }

    protected fun checkButtonEnabled(@IdRes id: Int) {
        onView(withId(id)).check(matches(isEnabled()))
    }

    protected fun checkButtonDisabled(@IdRes id: Int) {
        onView(withId(id)).check(matches(not(isEnabled())))
    }

    protected fun setupServer(server: MockWebServer, code: Int, jsonMockResponse: String) {
        server.enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(JsonReader.getStringFromJsonFile(jsonMockResponse)))
    }
}