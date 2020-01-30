package com.example.challengeeasy.simulation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challengeeasy.BaseViewModel
import com.example.challengeeasy.domain.model.SimulationVO
import com.example.challengeeasy.domain.source.SimulationDataSource
import com.example.challengeeasy.extension.toServerCurrency
import com.example.challengeeasy.extension.toServerDate
import com.example.challengeeasy.extension.toServerPercentage
import kotlinx.coroutines.launch
import java.math.BigDecimal

class SimulationViewModel(private val simulationDataSource: SimulationDataSource) :
    BaseViewModel() {

    private val state = MutableLiveData<SimulationViewState>()
    val viewState: LiveData<SimulationViewState> = state

    fun simulate(investedAmount: String, rate: String, maturityDate: String) {
        state.value = SimulationViewState.Loading(true)

        launch {
            try {
                val simulationVO = createSimulationVO(investedAmount, rate, maturityDate)
                state.value =
                    SimulationViewState.Success(simulationDataSource.simulate(simulationVO))
                state.value = SimulationViewState.Loading(false)
            } catch (throwable: Throwable) {
                state.value = SimulationViewState.Error(throwable)
                state.value = SimulationViewState.Loading(false)
            }
        }
    }

    private fun createSimulationVO(
        investedAmount: String,
        rate: String,
        maturityDate: String
    ): SimulationVO {
        return SimulationVO(
            BigDecimal(investedAmount.toServerCurrency()),
            rate = rate.toServerPercentage(),
            maturityDate = maturityDate.toServerDate()
        )
    }
}