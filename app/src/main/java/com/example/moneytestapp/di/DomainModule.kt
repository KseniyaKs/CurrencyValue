package com.example.moneytestapp.di

import com.example.moneytestapp.data.CurrencyRepository
import com.example.moneytestapp.domain.CurrencyInteractor
import com.example.moneytestapp.domain.CurrencyInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideCurrencyInteractor(repository: CurrencyRepository): CurrencyInteractor {
        return CurrencyInteractorImpl(repository)
    }
}