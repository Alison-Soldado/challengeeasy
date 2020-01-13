package com.example.challengeeasy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import com.example.challengeeasy.delegate.viewProvider
import com.example.challengeeasy.extension.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimulationActivity : AppCompatActivity() {

    private val simulationViewModel: SimulationViewModel by viewModel()
    private val editTextInvestedAmount: AppCompatEditText by viewProvider(R.id.activity_simulation_edit_text_invested_amount)
    private val editTextMaturityDate: AppCompatEditText by viewProvider(R.id.activity_simulation_edit_text_maturity_date)
    private val editTextRate: AppCompatEditText by viewProvider(R.id.activity_simulation_edit_text_rate)
    private val buttonSimulate: AppCompatButton by viewProvider(R.id.activity_simulation_button_simulate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)
        initObservable()
        setupClickButtonSimulate()
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
        simulationViewModel.simulationResult.observe(this, Observer {
            toast(it.investmentParameter.investedAmount.toString())
        })

        simulationViewModel.error.observe(this, Observer {  })

        simulationViewModel.loading.observe(this, Observer {  })
    }
}
