package com.example.challengeeasy

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class SimulationRunner : AndroidJUnitRunner() {

    override fun newApplication(
        classLoader: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(classLoader, SimulationApplicationTest::class.java.name, context)
    }
}