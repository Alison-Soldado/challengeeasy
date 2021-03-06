package com.example.challengeeasy.simulation

import androidx.lifecycle.Observer
import com.example.challengeeasy.repository.domain.model.SimulationResultVO
import com.example.challengeeasy.repository.domain.source.SimulationDataSource
import com.example.challengeeasy.apresentation.feature.simulation.SimulationViewModel
import com.example.challengeeasy.apresentation.feature.simulation.SimulationViewState
import com.example.challengeeasy.instantLiveDataAndCoroutinesRule
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception
import java.lang.RuntimeException
import java.net.UnknownHostException

class SimulationViewModelUnitTest {

    @get:Rule val rule = instantLiveDataAndCoroutinesRule

    private val observer : Observer<SimulationViewState> = mock()

    private val simulationResultVOTest: SimulationResultVO = mock()
    private val isLoadingTrue: Boolean = true
    private val isLoadingFalse: Boolean = false
    private val throwable: Throwable = mock()

    private val simulationDataSource: SimulationDataSource = mock()

    private val viewModel = SimulationViewModel(simulationDataSource)

    private val investedAmount = "R$10.000,00"
    private val rate = "100%"
    private val maturityDate = "21/12/2021"

    @Before
    fun setup() {
        viewModel.viewState.observeForever(observer)
    }

    @Test
    fun givenData_WhenSimulate_ThenReturnSuccess() {
        whenever(runBlocking {
            simulationDataSource.simulate(any())
        }) doReturn simulationResultVOTest

        viewModel.simulate(investedAmount, rate, maturityDate)

        verify(observer, times(1)).onChanged(SimulationViewState.Loading(isLoadingTrue))
        verify(observer, times(1)).onChanged(SimulationViewState.Success(simulationResultVOTest))
        verify(observer, times(1)).onChanged(SimulationViewState.Loading(isLoadingFalse))
        verify(observer, never()).onChanged(SimulationViewState.Error(throwable))

        verifyNoMoreInteractions(observer)
    }
}