package com.example.challengeeasy.apresentation.feature.simulation

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import com.example.challengeeasy.apresentation.feature.BaseActivity
import com.example.challengeeasy.R
import com.example.challengeeasy.apresentation.feature.bindView
import com.example.challengeeasy.apresentation.customview.SimulationEditText
import com.example.challengeeasy.apresentation.extension.toast
import com.example.challengeeasy.apresentation.extension.visibilityLoading
import com.example.challengeeasy.apresentation.feature.resultsimulation.ResultSimulationActivity
import com.example.challengeeasy.apresentation.feature.resultsimulation.ResultSimulationActivity.Companion.EXTRA_RESULT
import com.example.challengeeasy.infrastructure.injection.SimulationModules.initSimulationModule
import com.example.challengeeasy.repository.domain.validator.ValidationListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimulationActivity : BaseActivity(),
    ValidationListener {
    private val simulationViewModel: SimulationViewModel by viewModel()
    private val progressBarLoading: ProgressBar by bindView(R.id.activity_simulation_loading)
    private val editTextInvestedAmount: SimulationEditText by bindView(R.id.activity_simulation_edit_text_invested_amount)
    private val editTextMaturityDate: SimulationEditText by bindView(R.id.activity_simulation_edit_text_maturity_date)
    private val editTextRate: SimulationEditText by bindView(R.id.activity_simulation_edit_text_rate)
    private val buttonSimulate: AppCompatButton by bindView(R.id.activity_simulation_button_simulate)

    override fun initInjection() {
        initSimulationModule()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)
        initObservable()
        setupListeners()
        setupClickButtonSimulate()
    }

    private fun setupListeners() {
        editTextInvestedAmount.setValidationListener(this)
        editTextMaturityDate.setValidationListener(this)
        editTextRate.setValidationListener(this)
    }

    private fun setupClickButtonSimulate() {
        buttonSimulate.setOnClickListener {
            simulationViewModel.simulate(
                editTextInvestedAmount.text.toString(),
                editTextRate.text.toString(),
                editTextMaturityDate.text.toString()
            )
        }
    }

    private fun initObservable() {
        simulationViewModel.viewState.observe(this, Observer { viewState ->
            when (viewState) {
                is SimulationViewState.Success -> initActivity(viewState)
                is SimulationViewState.Error -> toast(R.string.activity_simulation_text_toast_error)
                is SimulationViewState.Loading -> progressBarLoading.visibilityLoading(viewState.isLoading)
            }
        })
    }

    private fun initActivity(viewState: SimulationViewState.Success) {
        val intentResult = Intent(this, ResultSimulationActivity::class.java)
        intentResult.putExtra(EXTRA_RESULT, viewState.simulationResult)
        startActivity(intentResult)
    }

    override fun validate() {
        buttonSimulate.isEnabled =
            editTextInvestedAmount.isValid() &&
                    editTextMaturityDate.isValid() &&
                    editTextRate.isValid()
    }
}
