package com.example.challengeeasy.repository.domain.model

import java.math.BigDecimal

data class SimulationVO(
    val investedAmount: BigDecimal,
    val index: String = "CDI",
    val rate: String,
    val isTaxFree: Boolean = false,
    val maturityDate: String
)