package com.example.challengeeasy.simulation

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import com.example.challengeeasy.BaseActivity
import com.example.challengeeasy.R
import com.example.challengeeasy.bindView
import com.example.challengeeasy.customview.SimulationEditText
import com.example.challengeeasy.extension.toast
import com.example.challengeeasy.extension.visibilityLoading
import com.example.challengeeasy.injection.initSimulationModule
import com.example.challengeeasy.resultsimulation.ResultSimulationActivity
import com.example.challengeeasy.resultsimulation.ResultSimulationActivity.Companion.EXTRA_RESULT
import com.example.challengeeasy.validator.ValidationListener
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
        simulationViewModel.viewState.observe(this, Observer {
            when (it) {
                is SimulationViewState.Success -> initActivity(it)
                is SimulationViewState.Error -> toast(R.string.activity_simulation_text_toast_error)
                is SimulationViewState.Loading -> progressBarLoading.visibilityLoading(it.isLoading)
            }
        })
    }

    private fun initActivity(it: SimulationViewState.Success) {
        val intentResult = Intent(this, ResultSimulationActivity::class.java)
        intentResult.putExtra(EXTRA_RESULT, it.simulationResult)
        startActivity(intentResult)
    }

    override fun validate() {
        buttonSimulate.isEnabled =
            editTextInvestedAmount.isValid() &&
                    editTextMaturityDate.isValid() &&
                    editTextRate.isValid()
    }
}
