package com.example.challengeeasy.resultsimulation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.example.challengeeasy.R
import com.example.challengeeasy.ResultTextView
import com.example.challengeeasy.delegate.viewProvider
import com.example.challengeeasy.domain.model.SimulationResultVO
import com.example.challengeeasy.extension.toBrazilianCurrency
import com.example.challengeeasy.extension.toDisplayDate
import com.example.challengeeasy.extension.toPercent

class ResultSimulationActivity : AppCompatActivity() {

    private val textViewValueResult: AppCompatTextView by viewProvider(R.id.activity_result_simulation_text_view_value_result)
    private val textViewYieldTotal: AppCompatTextView by viewProvider(R.id.activity_result_simulation_text_view_yield_total)
    private val textViewValueApply: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_value_apply)
    private val textViewValueGross: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_value_gross)
    private val textViewValueInvestment: ResultTextView by viewProvider(
        R.id.activity_result_simulation_text_view_value_investment
    )
    private val textViewIr: ResultTextView by viewProvider(R.id.actiivity_result_simulation_text_view_ir_investment)
    private val textViewNetValue: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_net_value)
    private val textViewDateRedemption: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_date_redemption)
    private val textViewContinuousDays: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_continuous_days)
    private val textViewMonthlyIncome: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_monthly_income)
    private val textViewPercentageCdi: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_percentage_cdi)
    private val textViewAnnualProfit: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_annual_profit)
    private val textViewPeriodProfit: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_period_profit)
    private val buttonSimulateAgain: AppCompatButton by viewProvider(R.id.activity_result_simulation_button_simulate_again)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_simulation)
        val simulationResultVO = initIntent()
        initComponents(simulationResultVO)
        setupClickButton()
    }

    private fun initIntent(): SimulationResultVO? {
        return intent.getParcelableExtra(EXTRA_RESULT)
    }

    private fun initComponents(simulationResultVO: SimulationResultVO?) {
        simulationResultVO?.let {
            textViewValueResult.text = it.grossAmount.toBrazilianCurrency()
            textViewYieldTotal.text = getString(
                R.string.activity_result_simulation_text_yield,
                it.grossAmountProfit.toBrazilianCurrency()
            )
            textViewValueApply.setText(it.investmentParameter.investedAmount.toBrazilianCurrency())
            textViewValueGross.setText(it.grossAmount.toBrazilianCurrency())
            textViewValueInvestment.setText(it.grossAmountProfit.toBrazilianCurrency())
            textViewIr.setText(it.taxesAmount.toBrazilianCurrency())
            textViewNetValue.setText(it.netAmount.toBrazilianCurrency())
            textViewDateRedemption.setText(it.investmentParameter.maturityDate.toDisplayDate())
            textViewContinuousDays.setText(it.investmentParameter.maturityTotalDays.toString())
            textViewMonthlyIncome.setText(it.monthlyGrossRateProfit.toDouble().toPercent())
            textViewPercentageCdi.setText(it.investmentParameter.rate.toPercent())
            textViewAnnualProfit.setText(it.annualGrossRateProfit.toDouble().toPercent())
            textViewPeriodProfit.setText(it.annualNetRateProfit.toDouble().toPercent())

        }
    }

    private fun setupClickButton() {
        buttonSimulateAgain.setOnClickListener { finish() }
    }

    companion object {
        const val EXTRA_RESULT = "resultSimulation"
    }
}