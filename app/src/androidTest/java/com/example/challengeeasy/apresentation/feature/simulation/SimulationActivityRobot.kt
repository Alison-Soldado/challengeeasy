package com.example.challengeeasy.apresentation.feature.simulation


import android.app.Activity
import android.content.Intent
import androidx.test.rule.ActivityTestRule
import com.example.challengeeasy.BaseRobotTest
import com.example.challengeeasy.R
import com.example.challengeeasy.mockSimulationResult
import okhttp3.mockwebserver.MockWebServer

fun simulate(func: SimulationActivityRobot.() -> Unit) = SimulationActivityRobot().apply { func() }


class SimulationActivityRobot : BaseRobotTest() {

    fun startActivity(activityRule: ActivityTestRule<SimulationActivity>) {
        activityRule.launchActivity(Intent())
    }

    fun setupServerSuccess(server: MockWebServer) {
        setupServer(server, 200, "fixture/success_simulate.json")
    }

    fun setupServerError(server: MockWebServer) {
        setupServer(server, 400,"fixture/error_simulate.json")
    }

    fun setAmountEditText(amount : String) {
        setEditText(R.id.activity_simulation_edit_text_invested_amount, amount)
    }

    fun setMaturityDate(date: String) {
        setEditText(R.id.activity_simulation_edit_text_maturity_date, date)
    }

    fun setRate(rate: String) {
        setEditText(R.id.activity_simulation_edit_text_rate, rate)
    }

    fun clickSimulate() = clickButton(R.id.activity_simulation_button_simulate)

    fun checkIntentToResultSimulation(activityRule: ActivityTestRule<SimulationActivity>) {
        val simulationResult = mockSimulationResult()
        checkIntent(activityRule.activity, "resultSimulation", simulationResult)
    }

    @Suppress("UNCHECKED_CAST")
    fun checkToastError(activityRule: ActivityTestRule<SimulationActivity>, text : String) {
        checkToast(text, activityRule as ActivityTestRule<Activity>)
    }

    fun checkButtonSimulateDisabled() {
        checkButtonDisabled(R.id.activity_simulation_button_simulate)
    }

    fun checkButtonSimulateEnabled() {
        checkButtonEnabled(R.id.activity_simulation_button_simulate)
    }
}