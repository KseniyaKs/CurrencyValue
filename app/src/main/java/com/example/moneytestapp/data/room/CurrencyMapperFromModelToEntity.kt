package com.example.moneytestapp.data.room

import com.example.moneytestapp.domain.CurrencyModel

interface CurrencyMapperFromModelToEntity{
    fun mapFromModelToEntity(currencyModel: CurrencyModel): CurrencyEntity
}
class CurrencyMapperFromModelToEntityImpl: CurrencyMapperFromModelToEntity {
    override fun mapFromModelToEntity(currencyModel: CurrencyModel): CurrencyEntity {
        return CurrencyEntity(
            currency_name = currencyModel.code,
            currency_value = currencyModel.value.toFloat(),
            isLike = currencyModel.isLike
        )
    }
}