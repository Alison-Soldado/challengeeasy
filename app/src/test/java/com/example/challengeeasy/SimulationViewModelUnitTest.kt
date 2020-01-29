package com.example.challengeeasy

import androidx.lifecycle.Observer
import com.example.challengeeasy.domain.model.SimulationResultVO
import com.example.challengeeasy.domain.source.SimulationDataSource
import com.example.challengeeasy.simulation.SimulationViewModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SimulationViewModelUnitTest {

    @get:Rule val rule = instantLiveDataAndCoroutinesRule

    private val observerLoading : Observer<Boolean> = mock()
    private val observerResultVO : Observer<SimulationResultVO> = mock()
    private val observerError : Observer<Throwable> = mock()

    private val loadingTest: Boolean = false
    private val simulationResultVOTest: SimulationResultVO = mock()
    private val errorTest: Throwable = mock()

    private val simulationDataSource: SimulationDataSource = mock()

    private val viewModel = SimulationViewModel(simulationDataSource)

    private val investedAmount = 1000.toString()
    private val rate = "100"
    private val maturityDate = "21-12-2021"

    @Before
    fun setup() {
        viewModel.loading.observeForever(observerLoading)
        viewModel.simulationResult.observeForever(observerResultVO)
        viewModel.error.observeForever(observerError)
    }

    @Test
    fun givenData_WhenSimulate_ThenReturnResult() {
        whenever(runBlocking {
            simulationDataSource.simulate(any())
        }) doReturn simulationResultVOTest

        viewModel.simulate(investedAmount, rate, maturityDate)

        verify(observerLoading, times(1)).onChanged(loadingTest)
        verify(observerResultVO, times(1)).onChanged(simulationResultVOTest)
        verify(observerLoading, times(1)).onChanged(loadingTest)
        verify(observerError, never()).onChanged(errorTest)

        verifyNoMoreInteractions(observerLoading)
        verifyNoMoreInteractions(observerResultVO)
        verifyNoMoreInteractions(observerError)
    }
}