package com.example.challengeeasy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import com.example.challengeeasy.delegate.viewProvider
import com.example.challengeeasy.extension.toServerCurrency
import com.example.challengeeasy.extension.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimulationActivity : AppCompatActivity(), ValidationListener {

    private val simulationViewModel: SimulationViewModel by viewModel()
    private val editTextInvestedAmount: SimulationEditText by viewProvider(R.id.activity_simulation_edit_text_invested_amount)
    private val editTextMaturityDate: SimulationEditText by viewProvider(R.id.activity_simulation_edit_text_maturity_date)
    private val editTextRate: SimulationEditText by viewProvider(R.id.activity_simulation_edit_text_rate)
    private val buttonSimulate: AppCompatButton by viewProvider(R.id.activity_simulation_button_simulate)

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
        simulationViewModel.simulationResult.observe(this, Observer { simulationResult ->
            val intentResult = Intent(this, ResultSimulationActivity::class.java)
            intentResult.putExtra("resultSimulation", simulationResult)
            startActivity(intentResult)
        })

        simulationViewModel.error.observe(this, Observer { toast("error") })

        simulationViewModel.loading.observe(this, Observer { toast("loading") })
    }

    override fun validate() {
        buttonSimulate.isEnabled = editTextInvestedAmount.isValid() && editTextMaturityDate.isValid() && editTextRate.isValid()
    }
}
