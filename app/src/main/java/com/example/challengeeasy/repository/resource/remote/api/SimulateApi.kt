package com.example.challengeeasy.repository.resource.remote.api

import com.example.challengeeasy.repository.resource.remote.response.SimulationResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigDecimal

interface SimulateApi {

    @GET("/calculator/simulate/")
    suspend fun simulateAsync(
        @Query("investedAmount") investedAmount: BigDecimal,
        @Query("index") index: String,
        @Query("rate") rate: String,
        @Query("isTaxFree") taxFree: Boolean,
        @Query("maturityDate") maturityDate: String
    ): SimulationResponse
}