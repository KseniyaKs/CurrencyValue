package com.example.moneytestapp.domain

import com.example.moneytestapp.data.CurrencyRepository
import javax.inject.Inject

interface CurrencyInteractor {

    suspend fun getCurrency():  List<CurrencyFullName>
    suspend fun getLatestCurrency(currency: String): List<CurrencyModel>

    suspend fun currencyLocalSave(currencyModel: CurrencyModel)
    suspend fun currencyLocalDelete(currencyModel: CurrencyModel)
    suspend fun getLocalSaveCurrency(): List<CurrencyModel>
}

class CurrencyInteractorImpl @Inject constructor(
    private val repository: CurrencyRepository,
) : CurrencyInteractor {
    override suspend fun getCurrency(): List<CurrencyFullName> {
        return repository.getCurrency()
    }

    override suspend fun getLatestCurrency(currency: String): List<CurrencyModel> {
        return repository.getLatestCurrencyValue(currency)
    }

    override suspend fun currencyLocalSave(currencyModel: CurrencyModel) {
        repository.currencyLocalSave(currencyModel)
    }


    override suspend fun currencyLocalDelete(curencyModel: CurrencyModel) {
        repository.currencyLocalDelete(curencyModel)
    }

    override suspend fun getLocalSaveCurrency(): List<CurrencyModel> {
        return repository.getLocalSaveCurrency()
    }


}