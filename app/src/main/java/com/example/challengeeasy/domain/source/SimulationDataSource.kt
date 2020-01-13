package com.example.challengeeasy.domain.source

import com.example.challengeeasy.domain.model.SimulationResultVO
import com.example.challengeeasy.domain.model.SimulationVO

interface SimulationDataSource {
    suspend fun simulate(simulationVO: SimulationVO): SimulationResultVO
}