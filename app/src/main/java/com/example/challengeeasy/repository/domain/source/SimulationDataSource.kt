package com.example.challengeeasy.repository.domain.source

import com.example.challengeeasy.repository.domain.model.SimulationResultVO
import com.example.challengeeasy.repository.domain.model.SimulationVO

interface SimulationDataSource {
    suspend fun simulate(simulationVO: SimulationVO): SimulationResultVO
}