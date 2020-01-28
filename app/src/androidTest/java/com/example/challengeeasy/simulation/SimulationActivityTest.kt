package com.example.challengeeasy.simulation

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.challengeeasy.config.createApi
import com.example.challengeeasy.config.provideOkHttpClient
import com.example.challengeeasy.config.provideRetrofit
import com.example.challengeeasy.domain.source.SimulationDataSource
import com.example.challengeeasy.injection.initSimulationModule
import com.example.challengeeasy.resource.remote.api.SimulateApi
import com.example.challengeeasy.resource.remote.source.SimulationRepository
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
class SimulationActivityTest {

    @get:Rule
    var activityRule = IntentsTestRule(SimulationActivity::class.java, false, false)

    private lateinit var server: MockWebServer
    private lateinit var modulesTest: List<Module>
    private val PATH_SIMULATION_MODULES = "com.example.challengeeasy.injection.SimulationModulesKt"

    @Before
    fun setup() {
        //TODO REFACTOR SETUP
        server = MockWebServer()
        server.start()

        val retrofitTest = provideRetrofit(server.url("/").toString(), provideOkHttpClient())

        val uiModuleTest = module {
            viewModel { SimulationViewModel(get()) }
        }

        val simulationModuleTest = module {
            single<SimulationDataSource> { SimulationRepository(createApi(retrofitTest)) }
        }

        val remoteModuleTest = module {
            single { provideOkHttpClient() }
            single { retrofitTest }
            single { createApi<SimulateApi>(retrofitTest) }
        }

        modulesTest = listOf(uiModuleTest, simulationModuleTest, remoteModuleTest)
        //TODO USE MOCKITO
        mockkStatic(PATH_SIMULATION_MODULES)
        every { initSimulationModule() } returns loadKoinModules(modulesTest)
    }

    @After
    fun tearDown() {
        server.shutdown()
        unloadKoinModules(modulesTest)
    }

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
            setupServer(server, "success_simulate.json")
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
            setupServer(server, "error_simulate.json")
            startActivity(activityRule)
            setAmountEditText("10000")
            setMaturityDate("21122021")
            setRate("100")
            clickSimulate()
            checkToastError(activityRule, "Something went wrong, try later!")
        }
    }
}