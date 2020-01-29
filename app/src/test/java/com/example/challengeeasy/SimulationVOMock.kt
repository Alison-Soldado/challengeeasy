package com.example.challengeeasy

import com.example.challengeeasy.domain.model.InvestmentParameterVO
import com.example.challengeeasy.domain.model.SimulationResultVO
import com.example.challengeeasy.domain.model.SimulationVO
import java.math.BigDecimal

fun simulationVOMock(): SimulationVO {
    return SimulationVO(
        BigDecimal(1000),
        "CDI",
        "100",
        false,
        "21-12-2021"
    )
}

fun simulationResultMock(): SimulationResultVO {
    return SimulationResultVO(
        investmentParameterMock(),
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


fun investmentParameterMock(): InvestmentParameterVO {
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