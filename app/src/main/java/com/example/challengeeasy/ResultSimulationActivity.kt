package com.example.challengeeasy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
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
    private val textViewValueInvestment: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_value_investment)
    private val textViewIr: ResultTextView by viewProvider(R.id.actiivity_result_simulation_text_view_ir_investment)
    private val textViewNetValue: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_net_value)
    private val textViewDateRedemption: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_date_redemption)
    private val textViewContinuousDays: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_continuous_days)
    private val textViewMonthlyIncome: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_monthly_income)
    private val textViewPercentageCdi: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_percentage_cdi)
    private val textViewAnnualProfit: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_annual_profit)
    private val textViewPeriodProfit: ResultTextView by viewProvider(R.id.activity_result_simulation_text_view_period_profit)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_simulation)

        val simulationResultVO: SimulationResultVO? = intent.getParcelableExtra("resultSimulation")

        textViewValueResult.text = simulationResultVO?.grossAmount?.toBrazilianCurrency() ?: ""
        textViewYieldTotal.text = getString(R.string.activity_result_simulation_text_yield, simulationResultVO?.grossAmountProfit?.toBrazilianCurrency())
        textViewValueApply.setText(simulationResultVO?.investmentParameter?.investedAmount?.toBrazilianCurrency() ?: "")
        textViewValueGross.setText(simulationResultVO?.grossAmount?.toBrazilianCurrency() ?: "")
        textViewValueInvestment.setText(simulationResultVO?.grossAmountProfit?.toBrazilianCurrency() ?: "")
        textViewIr.setText(simulationResultVO?.taxesAmount?.toBrazilianCurrency() ?: "")
        textViewNetValue.setText(simulationResultVO?.netAmount?.toBrazilianCurrency() ?: "")
        textViewDateRedemption.setText(simulationResultVO?.investmentParameter?.maturityDate?.toDisplayDate() ?: "")
        textViewContinuousDays.setText(simulationResultVO?.investmentParameter?.maturityTotalDays.toString())
        textViewMonthlyIncome.setText(simulationResultVO?.monthlyGrossRateProfit?.toDouble()?.toPercent() ?: "")
        textViewPercentageCdi.setText(simulationResultVO?.investmentParameter?.rate?.toPercent() ?: "")
        textViewAnnualProfit.setText(simulationResultVO?.annualGrossRateProfit?.toDouble()?.toPercent() ?: "")
        textViewPeriodProfit.setText(simulationResultVO?.annualNetRateProfit?.toDouble()?.toPercent() ?: "")
    }
}