package com.example.moneytestapp.data.network

import com.example.moneytestapp.data.popular.CurrencyNameListResponse
import com.example.moneytestapp.data.popular.LatestCurrencyValueResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("latest")
    suspend fun getLatestCurrencyValue(
        @Query("base") baseValue: String,
    ): Response<LatestCurrencyValueResponse>

    @GET("symbols")
    suspend fun getCurrency(
    ): Response<CurrencyNameListResponse>

}

