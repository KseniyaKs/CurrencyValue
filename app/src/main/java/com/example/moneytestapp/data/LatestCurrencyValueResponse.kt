package com.example.moneytestapp.data.popular

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject


data class LatestCurrencyValueResponse (
   @SerializedName("rates") val rates: Map<String, Number>,
)

data class CurrencyNameListResponse (
    @SerializedName("symbols") val symbols: Map<String, CurrencyName>
)

data class CurrencyName(
    @SerializedName("description") val description: String,
    @SerializedName("code") val code: String
    )


