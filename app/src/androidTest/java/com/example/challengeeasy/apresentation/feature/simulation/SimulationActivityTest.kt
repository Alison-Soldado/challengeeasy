package com.example.challengeeasy.apresentation.feature.simulation

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.challengeeasy.BaseInstrumentedTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@RunWith(AndroidJUnit4::class)
class SimulationActivityTest : BaseInstrumentedTest() {

    @get:Rule
    var activityRule = IntentsTestRule(SimulationActivity::class.java, false, false)

    @Test
    fun givenFillFieldsWrong_WhenClickSimulate_ThenButtonDisabled() {
        simulate {
            startActivity(activityRule)
            setAmountEditText("")
            setMaturityDate("")
            setRate("")
            checkButtonSimulateDisabled()
        }
    }

    @Test
    fun givenFillFieldsCorrect_WhenClickSimulate_ThenButtonEnabled() {
        simulate {
            startActivity(activityRule)
            setAmountEditText("10000")
            setMaturityDate("21122021")
            setRate("100")
            checkButtonSimulateEnabled()
        }
    }

    @Test
    fun givenFillFields_WhenClickSimulate_ThenShowResultSimulation() {
        simulate {
            setupServerSuccess(server)
            startActivity(activityRule)
            setAmountEditText("10000")
            setMaturityDate("21122021")
            setRate("100")
            clickSimulate()
            checkIntentToResultSimulation(activityRule)
        }
    }

    @Test
    fun givenFillFields_WhenClickSimulate_ThenShowError() {
        simulate {
            setupServerError(server)
            startActivity(activityRule)
            setAmountEditText("10000")
            setMaturityDate("21122021")
            setRate("100")
            clickSimulate()
            checkToastError(activityRule, "Something went wrong, try later!")
        }
    }
}