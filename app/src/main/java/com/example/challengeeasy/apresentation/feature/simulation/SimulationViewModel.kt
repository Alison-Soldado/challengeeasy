package com.example.challengeeasy.apresentation.feature.simulation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challengeeasy.apresentation.feature.BaseViewModel
import com.example.challengeeasy.repository.domain.model.SimulationVO
import com.example.challengeeasy.repository.domain.source.SimulationDataSource
import com.example.challengeeasy.apresentation.extension.toServerCurrency
import com.example.challengeeasy.apresentation.extension.toServerDate
import com.example.challengeeasy.apresentation.extension.toServerPercentage
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