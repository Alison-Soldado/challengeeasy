package com.example.challengeeasy

import com.example.challengeeasy.apresentation.feature.simulation.SimulationViewModel
import com.example.challengeeasy.infrastructure.injection.SimulationModules
import com.example.challengeeasy.infrastructure.network.createApi
import com.example.challengeeasy.infrastructure.network.provideOkHttpClient
import com.example.challengeeasy.infrastructure.network.provideRetrofit
import com.example.challengeeasy.repository.domain.source.SimulationDataSource
import com.example.challengeeasy.repository.resource.remote.api.SimulateApi
import com.example.challengeeasy.repository.resource.remote.source.SimulationRepository
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

open class BaseInstrumentedTest {

    protected lateinit var server: MockWebServer
    private lateinit var modulesTest: List<Module>

    companion object {
        private const val PACKAGE_SIMULATION_MODULES = "com.example.challengeeasy.infrastructure.injection.SimulationModulesKt"
    }

    @Before
    fun setup() {
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
        val mockSimulationModules: SimulationModules = mock(SimulationModules::class.java)
        `when` { mockSimulationModules.initSimulationModule() }.then {  loadKoinModules(modulesTest) }
    }


    @After
    fun tearDown() {
        server.shutdown()
        unloadKoinModules(modulesTest)
    }
}