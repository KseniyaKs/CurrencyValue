package com.example.moneytestapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CurrencyEntity(
    @PrimaryKey
    @ColumnInfo
        (name = "currency_name") val currency_name: String,
    @ColumnInfo
        (name = "currency_value") val currency_value: Float,
    var isLike: Boolean = false
)
