package com.example.moneytestapp.di

import com.example.moneytestapp.data.CurrencyRepository
import com.example.moneytestapp.data.CurrencyRepositoryImpl
import com.example.moneytestapp.data.network.Api
import com.example.moneytestapp.data.network.ResponseMapper
import com.example.moneytestapp.data.network.ResponseMapperImpl
import com.example.moneytestapp.domain.CurrencyInteractor
import com.example.moneytestapp.domain.CurrencyInteractorImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module //(includes = [ApiModule::class])
class DomainModule {

    @Provides
    @Singleton
    fun provideCurrencyInteractor(repository: CurrencyRepository): CurrencyInteractor {
        return CurrencyInteractorImpl(repository)
    }
}