package com.example.moneytestapp.di

import com.example.moneytestapp.data.CurrencyRepository
import com.example.moneytestapp.data.CurrencyRepositoryImpl
import com.example.moneytestapp.data.network.Api
import com.example.moneytestapp.data.network.ResponseMapper
import com.example.moneytestapp.data.room.AppDatabase
import com.example.moneytestapp.data.room.CurrencyDao
import com.example.moneytestapp.data.room.CurrencyMapperFromModelToEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module //(includes = [ApiModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideCurrencyRepository(
        responseMapper: ResponseMapper,
        api: Api,
        dataBase: CurrencyDao,
        mapper: CurrencyMapperFromModelToEntity
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(responseMapper, api, dataBase, mapper)
    }
}