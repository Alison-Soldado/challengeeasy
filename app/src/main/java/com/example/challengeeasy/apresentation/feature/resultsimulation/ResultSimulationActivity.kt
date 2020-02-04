package com.example.challengeeasy.apresentation.feature.resultsimulation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.example.challengeeasy.R
import com.example.challengeeasy.apresentation.feature.bindView
import com.example.challengeeasy.apresentation.customview.ResultTextView
import com.example.challengeeasy.repository.domain.model.SimulationResultVO
import com.example.challengeeasy.apresentation.extension.toBrazilCurrency
import com.example.challengeeasy.apresentation.extension.toDisplayDate
import com.example.challengeeasy.apresentation.extension.toPercent
import com.example.challengeeasy.apresentation.extension.toSpannableColorPrimary

class ResultSimulationActivity : AppCompatActivity() {

    private val textViewValueResult: AppCompatTextView by bindView(R.id.activity_result_simulation_text_view_value_result)
    private val textViewYieldTotal: AppCompatTextView by bindView(R.id.activity_result_simulation_text_view_yield_total)
    private val textViewValueApply: ResultTextView by bindView(R.id.activity_result_simulation_text_view_value_apply)
    private val textViewValueGross: ResultTextView by bindView(R.id.activity_result_simulation_text_view_value_gross)
    private val textViewValueInvestment: ResultTextView by bindView(R.id.activity_result_simulation_text_view_value_investment)
    private val textViewIr: ResultTextView by bindView(R.id.actiivity_result_simulation_text_view_ir_investment)
    private val textViewNetValue: ResultTextView by bindView(R.id.activity_result_simulation_text_view_net_value)
    private val textViewDateRedemption: ResultTextView by bindView(R.id.activity_result_simulation_text_view_date_redemption)
    private val textViewContinuousDays: ResultTextView by bindView(R.id.activity_result_simulation_text_view_continuous_days)
    private val textViewMonthlyIncome: ResultTextView by bindView(R.id.activity_result_simulation_text_view_monthly_income)
    private val textViewPercentageCdi: ResultTextView by bindView(R.id.activity_result_simulation_text_view_percentage_cdi)
    private val textViewAnnualProfit: ResultTextView by bindView(R.id.activity_result_simulation_text_view_annual_profit)
    private val textViewPeriodProfit: ResultTextView by bindView(R.id.activity_result_simulation_text_view_period_profit)
    private val buttonSimulateAgain: AppCompatButton by bindView(R.id.activity_result_simulation_button_simulate_again)
    private var indexStart: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_simulation)
        val simulationResultVO = initIntent()
        initComponents(simulationResultVO)
        setupClickButton()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initIntent(): SimulationResultVO? {
        return intent.getParcelableExtra(EXTRA_RESULT)
    }

    private fun initComponents(simulationResultVO: SimulationResultVO?) {
        simulationResultVO?.let {
            textViewValueResult.text = it.grossAmount.toBrazilCurrency()
            val textYield = getStringYield(it)
            textViewYieldTotal.toSpannableColorPrimary(textYield, indexStart, textYield.length)
            textViewValueApply.setText(it.investmentParameter.investedAmount.toBrazilCurrency())
            textViewValueGross.setText(it.grossAmount.toBrazilCurrency())
            textViewValueInvestment.setText(it.grossAmountProfit.toBrazilCurrency())
            textViewIr.setText("${it.taxesAmount.toBrazilCurrency()} (${it.taxesRate.toDouble().toPercent()})")
            textViewNetValue.setText(it.netAmount.toBrazilCurrency())
            textViewDateRedemption.setText(it.investmentParameter.maturityDate.toDisplayDate())
            textViewContinuousDays.setText(it.investmentParameter.maturityTotalDays.toString())
            textViewMonthlyIncome.setText(it.monthlyGrossRateProfit.toDouble().toPercent())
            textViewPercentageCdi.setText(it.investmentParameter.rate.toPercent())
            textViewAnnualProfit.setText(it.annualGrossRateProfit.toDouble().toPercent())
            textViewPeriodProfit.setText(it.annualNetRateProfit.toDouble().toPercent())
        }
    }

    private fun getStringYield(simulationResultVO: SimulationResultVO): String {
        return getString(
            R.string.activity_result_simulation_text_yield,
            simulationResultVO.grossAmountProfit.toBrazilCurrency()
        ).apply {
            indexStart = indexOf(simulationResultVO.grossAmountProfit.toBrazilCurrency())
        }
    }

    private fun setupClickButton() {
        buttonSimulateAgain.setOnClickListener { finish() }
    }

    companion object {
        const val EXTRA_RESULT = "resultSimulation"
    }
}