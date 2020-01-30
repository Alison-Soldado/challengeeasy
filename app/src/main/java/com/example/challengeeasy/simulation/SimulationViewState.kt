package com.example.challengeeasy.simulation

import com.example.challengeeasy.domain.model.SimulationResultVO

sealed class SimulationViewState {
    data class Success(val simulationResult: SimulationResultVO) : SimulationViewState()
    data class Error(val throwable: Throwable) : SimulationViewState()
    data class Loading(val isLoading: Boolean) : SimulationViewState()
}