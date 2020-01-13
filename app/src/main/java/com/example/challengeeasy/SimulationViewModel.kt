package com.example.challengeeasy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengeeasy.domain.model.SimulationResultVO
import com.example.challengeeasy.domain.model.SimulationVO
import com.example.challengeeasy.domain.source.SimulationDataSource
import kotlinx.coroutines.*
import java.math.BigDecimal

class SimulationViewModel(private val simulationDataSource: SimulationDataSource) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModeScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _simulationResult: MutableLiveData<SimulationResultVO> = MutableLiveData()
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    private val _error: MutableLiveData<Throwable> = MutableLiveData()

    val simulationResult: LiveData<SimulationResultVO> get() = _simulationResult
    val loading: LiveData<Boolean> get() = _loading
    val error: LiveData<Throwable> get() = _error

    fun simulate(investedAmount: String, rate: String, maturityDate: String) {
        _loading.value = true
        val simulationVO = SimulationVO(investedAmount = BigDecimal(investedAmount), rate = rate, maturityDate = maturityDate)
        viewModeScope.launch {
            try {
                _simulationResult.value = simulationDataSource.simulate(simulationVO)
                _loading.value = false
            } catch (throwable: Throwable) {
//                _simulationResult.value = simulationVO.empty
                _error.value = throwable
            } finally {
                _loading.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}