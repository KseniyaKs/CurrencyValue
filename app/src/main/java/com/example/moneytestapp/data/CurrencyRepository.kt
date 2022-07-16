package com.example.moneytestapp.data

import com.example.moneytestapp.data.network.Api
import com.example.moneytestapp.data.network.ResponseMapper
import com.example.moneytestapp.data.popular.CurrencyNameListResponse
import com.example.moneytestapp.data.room.CurrencyDao
import com.example.moneytestapp.data.room.CurrencyMapperFromModelToEntity
import com.example.moneytestapp.domain.CurrencyFullName
import com.example.moneytestapp.domain.CurrencyModel
import javax.inject.Inject


interface CurrencyRepository {
    suspend fun getCurrency(): List<CurrencyFullName>
    suspend fun getLatestCurrencyValue(currency: String): List<CurrencyModel>

    suspend fun currencyLocalSave(currencyModel: CurrencyModel)
    suspend fun currencyLocalDelete(currencyModel: CurrencyModel)
    suspend fun getLocalSaveCurrency(): List<CurrencyModel>
}


class CurrencyRepositoryImpl @Inject constructor(
    private val responseMapper: ResponseMapper,
    private val api: Api,
    private val dataBase: CurrencyDao,
    private val mapper: CurrencyMapperFromModelToEntity
) : CurrencyRepository {

    override suspend fun getCurrency(): List<CurrencyFullName> {
        val response = responseMapper.map(api.getCurrency())
        return response.symbols.flatMap {
            listOf(CurrencyFullName(it.key, it.value.description))
        }
    }

    override suspend fun getLatestCurrencyValue(currency: String): List<CurrencyModel> {
        val response = responseMapper.map(api.getLatestCurrencyValue(currency))

        val currencyList = response.rates.flatMap {
            listOf(CurrencyModel(it.key, it.value))
        }

        currencyList.forEach { currencyModel ->
            if ( getLocalSaveCurrency().find { it.code == currencyModel.code } != null) {
                currencyModel.isLike = true
            }
        }
        return currencyList
    }

    override suspend fun currencyLocalSave(curencyModel: CurrencyModel) {
        val entity = mapper.mapFromModelToEntity(curencyModel)
        dataBase.insert(entity)
    }

    override suspend fun currencyLocalDelete(curencyModel: CurrencyModel) {
        val entity = mapper.mapFromModelToEntity(curencyModel)
        dataBase.delete(entity)
    }

    override suspend fun getLocalSaveCurrency(): List<CurrencyModel> {
        return dataBase.getAll()?.map {
            CurrencyModel(
                code = it.currency_name,
                value = it.currency_value,
                isLike = it.isLike
            )
        } ?: listOf()
    }


}