package com.example.challengeeasy.repository.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class SimulationResultVO(
    val investmentParameter: InvestmentParameterVO,
    val grossAmount: BigDecimal,
    val taxesAmount: BigDecimal,
    val netAmount: BigDecimal,
    val grossAmountProfit: BigDecimal,
    val netAmountProfit: BigDecimal,
    val annualGrossRateProfit: BigDecimal,
    val monthlyGrossRateProfit: BigDecimal,
    val dailyGrossRateProfit: BigDecimal,
    val taxesRate: BigDecimal,
    val rateProfit: BigDecimal,
    val annualNetRateProfit: BigDecimal
) : Parcelable

@Parcelize
data class InvestmentParameterVO(
    val investedAmount: BigDecimal,
    val yearlyInterestRate: BigDecimal,
    val maturityTotalDays: Int,
    val maturityBusinessDays: Int,
    val maturityDate: String,
    val rate: Double,
    val isTaxFree: Boolean
) : Parcelable