package com.example.challengeeasy.simulation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challengeeasy.BaseViewModel
import com.example.challengeeasy.domain.model.SimulationResultVO
import com.example.challengeeasy.domain.model.SimulationVO
import com.example.challengeeasy.domain.source.SimulationDataSource
import com.example.challengeeasy.extension.toServerCurrency
import com.example.challengeeasy.extension.toServerDate
import com.example.challengeeasy.extension.toServerPercentage
import kotlinx.coroutines.launch
import java.math.BigDecimal

class SimulationViewModel(private val simulationDataSource: SimulationDataSource) : BaseViewModel() {

    private val _simulationResult: MutableLiveData<SimulationResultVO> = MutableLiveData()
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    private val _error: MutableLiveData<Throwable> = MutableLiveData()

    val simulationResult: LiveData<SimulationResultVO> get() = _simulationResult
    val loading: LiveData<Boolean> get() = _loading
    val error: LiveData<Throwable> get() = _error

    fun simulate(investedAmount: String, rate: String, maturityDate: String) {
        _loading.value = true

        launch {
            try {
                val simulationVO = SimulationVO(
                    BigDecimal(investedAmount.toServerCurrency()),
                    rate = rate.toServerPercentage(),
                    maturityDate = maturityDate.toServerDate()
                )
                _simulationResult.value = simulationDataSource.simulate(simulationVO)
                _loading.value = false
            } catch (throwable: Throwable) {
                _error.value = throwable
            } finally {
                _loading.value = false
            }
        }
    }
}