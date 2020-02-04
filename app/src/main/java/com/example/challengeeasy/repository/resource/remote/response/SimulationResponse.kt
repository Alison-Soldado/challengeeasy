package com.example.challengeeasy.repository.resource.remote.response

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class SimulationResponse(
    @SerializedName("investmentParameter") val investmentParameter: InvestmentParameter,
    @SerializedName("grossAmount") val grossAmount: BigDecimal,
    @SerializedName("taxesAmount") val taxesAmount: BigDecimal,
    @SerializedName("netAmount") val netAmount: BigDecimal,
    @SerializedName("grossAmountProfit") val grossAmountProfit: BigDecimal,
    @SerializedName("netAmountProfit") val netAmountProfit: BigDecimal,
    @SerializedName("annualGrossRateProfit") val annualGrossRateProfit: BigDecimal,
    @SerializedName("monthlyGrossRateProfit") val monthlyGrossRateProfit: BigDecimal,
    @SerializedName("dailyGrossRateProfit") val dailyGrossRateProfit: BigDecimal,
    @SerializedName("taxesRate") val taxesRate: BigDecimal,
    @SerializedName("rateProfit") val rateProfit: BigDecimal,
    @SerializedName("annualNetRateProfit") val annualNetRateProfit: BigDecimal
)

class InvestmentParameter(
    @SerializedName("investedAmount") val investedAmount: BigDecimal,
    @SerializedName("yearlyInterestRate") val yearlyInterestRate: BigDecimal,
    @SerializedName("maturityTotalDays") val maturityTotalDays: Int,
    @SerializedName("maturityBusinessDays") val maturityBusinessDays: Int,
    @SerializedName("maturityDate") val maturityDate: String,
    @SerializedName("rate") val rate: Double,
    @SerializedName("isTaxFree") val isTaxFree: Boolean
)