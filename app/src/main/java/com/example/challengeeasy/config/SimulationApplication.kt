package com.example.challengeeasy.config

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class SimulationApplication : Application() {

    private val appModules by lazy {
        listOf(remoteModule, simulationModule, uiModule)
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SimulationApplication)
            modules(appModules)
        }
    }
}