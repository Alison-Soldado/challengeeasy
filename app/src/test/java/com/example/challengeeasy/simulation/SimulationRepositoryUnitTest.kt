package com.example.challengeeasy.simulation

import com.example.challengeeasy.createSimulationVO
import com.example.challengeeasy.repository.domain.model.SimulationResultVO
import com.example.challengeeasy.repository.resource.remote.api.SimulateApi
import com.example.challengeeasy.repository.resource.remote.response.SimulationResponse
import com.example.challengeeasy.repository.resource.remote.source.SimulationRepository
import com.example.challengeeasy.simulationResponseMock
import com.example.challengeeasy.simulationResultMock
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test


class SimulationRepositoryUnitTest {

    private val simulationResultVO: SimulationResultVO =
        simulationResultMock()
    private val simulationResponse: SimulationResponse =
        simulationResponseMock()
    private val simulateApi: SimulateApi = mock()
    private val simulationRepository = SimulationRepository(simulateApi)

    @Test
    fun givenSimulation_WhenSimulate_ThenAssertResultEquals() {
        val simulationVO =
            createSimulationVO("R$10.000,00", "100%", "21/12/2021")

        whenever(runBlocking {
            simulateApi.simulateAsync(
                simulationVO.investedAmount,
                simulationVO.index,
                simulationVO.rate,
                simulationVO.isTaxFree,
                simulationVO.maturityDate)
        }) doReturn simulationResponse

        val result = runBlocking { simulationRepository.simulate(simulationVO) }

        assertEquals(simulationResultVO, result)
    }
}