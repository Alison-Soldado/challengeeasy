package com.example.challengeeasy

import com.example.challengeeasy.repository.domain.model.InvestmentParameterVO
import com.example.challengeeasy.repository.domain.model.SimulationResultVO
import com.example.challengeeasy.repository.domain.model.SimulationVO
import com.example.challengeeasy.apresentation.extension.toServerCurrency
import com.example.challengeeasy.apresentation.extension.toServerDate
import com.example.challengeeasy.apresentation.extension.toServerPercentage
import com.example.challengeeasy.repository.resource.remote.response.InvestmentParameter
import com.example.challengeeasy.repository.resource.remote.response.SimulationResponse
import java.math.BigDecimal

fun simulationResultMock(): SimulationResultVO {
    return SimulationResultVO(
        investmentParameterResultMock(),
        BigDecimal(60528.20),
        BigDecimal(4230.78),
        BigDecimal(56297.42),
        BigDecimal(28205.20),
        BigDecimal(23974.42),
        BigDecimal(87.26),
        BigDecimal(0.76),
        BigDecimal(0.000445330025305748),
        BigDecimal(15.0),
        BigDecimal(9.5512),
        BigDecimal(74.17)
    )
}


fun investmentParameterResultMock(): InvestmentParameterVO {
    return InvestmentParameterVO(
        BigDecimal(32323.0),
        BigDecimal(9.5512),
        1981,
        1409,
        "2023-03-03T00:00:00",
        123.0,
        false
    )
}

fun simulationResponseMock(): SimulationResponse {
    return SimulationResponse(
        investmentParameterResponseMock(),
        BigDecimal(60528.20),
        BigDecimal(4230.78),
        BigDecimal(56297.42),
        BigDecimal(28205.20),
        BigDecimal(23974.42),
        BigDecimal(87.26),
        BigDecimal(0.76),
        BigDecimal(0.000445330025305748),
        BigDecimal(15.0),
        BigDecimal(9.5512),
        BigDecimal(74.17)
    )
}


fun investmentParameterResponseMock(): InvestmentParameter {
    return InvestmentParameter(
        BigDecimal(32323.0),
        BigDecimal(9.5512),
        1981,
        1409,
        "2023-03-03T00:00:00",
        123.0,
        false
    )
}

fun createSimulationVO(
    investedAmount: String,
    rate: String,
    maturityDate: String
): SimulationVO {
    return SimulationVO(
        BigDecimal(investedAmount.toServerCurrency()),
        rate = rate.toServerPercentage(),
        maturityDate = maturityDate.toServerDate()
    )
}