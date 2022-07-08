package com.example.moneytestapp.data.network

import com.example.moneytestapp.BuildConfig
import com.example.moneytestapp.data.popular.CurrencyNameListResponse
import com.example.moneytestapp.data.popular.LatestCurrencyValueResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("exchangerates_data/latest")
    suspend fun getLatestCurrencyValue(
        @Query("base") baseValue: String,
        @Query("apikey") api_key: String = BuildConfig.API_KEY
    ): Response<LatestCurrencyValueResponse>

    @GET("exchangerates_data/symbols")
    suspend fun getCurrency(
        @Query("apikey") api_key: String = BuildConfig.API_KEY
    ): Response<CurrencyNameListResponse>

}


//    https://api.apilayer.com/exchangerates_data/symbols?apikey=OcXT7Iunnb7LoN1JulSb7579st9SPrb7

//    https://api.apilayer.com/exchangerates_data/latest?apikey=OcXT7Iunnb7LoN1JulSb7579st9SPrb7

//    https://api.apilayer.com/exchangerates_data/convert?from=EUR&to=USD&amount=57&apikey=OcXT7Iunnb7LoN1JulSb7579st9SPrb7

