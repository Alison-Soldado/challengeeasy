package com.example.challengeeasy.repository.resource.remote.parse

import com.example.challengeeasy.repository.domain.model.InvestmentParameterVO
import com.example.challengeeasy.repository.domain.model.SimulationResultVO
import com.example.challengeeasy.repository.resource.remote.response.InvestmentParameter
import com.example.challengeeasy.repository.resource.remote.response.SimulationResponse

fun SimulationResponse.toModel(): SimulationResultVO {
    return SimulationResultVO(
        this.investmentParameter.toModel(),
        this.grossAmount,
        this.taxesAmount,
        this.netAmount,
        this.grossAmountProfit,
        this.netAmountProfit,
        this.annualGrossRateProfit,
        this.monthlyGrossRateProfit,
        this.dailyGrossRateProfit,
        this.taxesRate,
        this.rateProfit,
        this.annualNetRateProfit
    )
}

fun InvestmentParameter.toModel(): InvestmentParameterVO {
    return InvestmentParameterVO(
        this.investedAmount,
        this.yearlyInterestRate,
        this.maturityTotalDays,
        this.maturityBusinessDays,
        this.maturityDate,
        this.rate,
        this.isTaxFree
    )
}