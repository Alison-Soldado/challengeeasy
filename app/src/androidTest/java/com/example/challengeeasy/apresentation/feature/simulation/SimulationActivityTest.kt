package com.example.challengeeasy.apresentation.feature.simulation

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.challengeeasy.BaseInstrumentedTest
import com.example.challengeeasy.infrastructure.network.createApi
import com.example.challengeeasy.infrastructure.network.provideOkHttpClient
import com.example.challengeeasy.infrastructure.network.provideRetrofit
import com.example.challengeeasy.repository.domain.source.SimulationDataSource
import com.example.challengeeasy.infrastructure.injection.initSimulationModule
import com.example.challengeeasy.repository.resource.remote.api.SimulateApi
import com.example.challengeeasy.repository.resource.remote.source.SimulationRepository
import io.mockk.every
import io.mockk.mockkStatic
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module


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