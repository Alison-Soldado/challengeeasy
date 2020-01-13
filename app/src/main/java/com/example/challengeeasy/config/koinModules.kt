package com.example.challengeeasy.config

import com.example.challengeeasy.SimulationViewModel
import com.example.challengeeasy.domain.source.SimulationDataSource
import com.example.challengeeasy.resource.remote.api.SimulateApi
import com.example.challengeeasy.resource.remote.source.SimulationRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { SimulationViewModel(get()) }
}

val simulationModule = module {
    single<SimulationDataSource> { SimulationRepository(get()) }
}

val remoteModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { createApi<SimulateApi>(get()) }
}