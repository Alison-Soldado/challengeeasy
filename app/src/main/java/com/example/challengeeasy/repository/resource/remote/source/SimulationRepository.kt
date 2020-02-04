package com.example.challengeeasy.repository.resource.remote.source

import com.example.challengeeasy.repository.domain.model.SimulationVO
import com.example.challengeeasy.repository.domain.source.SimulationDataSource
import com.example.challengeeasy.repository.resource.remote.api.SimulateApi
import com.example.challengeeasy.repository.resource.remote.parse.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SimulationRepository(private val simulateApi: SimulateApi) : SimulationDataSource {

    override suspend fun simulate(simulationVO: SimulationVO) = withContext(Dispatchers.IO) {
        simulateApi.simulateAsync(
            simulationVO.investedAmount,
            simulationVO.index,
            simulationVO.rate,
            simulationVO.isTaxFree,
            simulationVO.maturityDate).toModel()
    }
}