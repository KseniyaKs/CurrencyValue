package com.example.moneytestapp.domain


data class CurrencyModel(
    val code: String,
    val value: Number,
    var isLike: Boolean = false
)

data class CurrencyFullName(
    val code: String,
    val fullName: String
)

//data class Currency(
//    val description: String,
//    val code: String
//)


