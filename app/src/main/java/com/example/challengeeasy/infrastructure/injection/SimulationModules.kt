package com.example.challengeeasy.infrastructure.injection

import com.example.challengeeasy.BuildConfig
import com.example.challengeeasy.apresentation.feature.simulation.SimulationViewModel
import com.example.challengeeasy.infrastructure.network.createApi
import com.example.challengeeasy.infrastructure.network.provideOkHttpClient
import com.example.challengeeasy.infrastructure.network.provideRetrofit
import com.example.challengeeasy.repository.domain.source.SimulationDataSource
import com.example.challengeeasy.repository.resource.remote.api.SimulateApi
import com.example.challengeeasy.repository.resource.remote.source.SimulationRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

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

internal fun initSimulationModule() = loadKoinModules(listOf(uiModule, simulationModule, remoteModule))