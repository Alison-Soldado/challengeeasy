package com.example.challengeeasy.injection

import com.example.challengeeasy.BuildConfig
import com.example.challengeeasy.simulation.SimulationViewModel
import com.example.challengeeasy.config.createApi
import com.example.challengeeasy.config.provideOkHttpClient
import com.example.challengeeasy.config.provideRetrofit
import com.example.challengeeasy.domain.source.SimulationDataSource
import com.example.challengeeasy.resource.remote.api.SimulateApi
import com.example.challengeeasy.resource.remote.source.SimulationRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object SimulationModules {
    private const val URL = BuildConfig.BASE_URL

    private val uiModule = module {
        viewModel { SimulationViewModel(get()) }
    }

    private val simulationModule = module {
        single<SimulationDataSource> { SimulationRepository(get()) }
    }

    private val remoteModule = module {
        single { provideOkHttpClient() }
        single { provideRetrofit(URL, get()) }
        single { createApi<SimulateApi>(get()) }
    }

    fun initSimulationModule() = loadKoinModules(listOf(uiModule, simulationModule, remoteModule))
}