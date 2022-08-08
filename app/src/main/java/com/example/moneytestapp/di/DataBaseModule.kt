package com.example.moneytestapp.di

import android.content.Context
import androidx.room.Room
import com.example.moneytestapp.data.room.AppDatabase
import com.example.moneytestapp.data.room.CurrencyDao
import com.example.moneytestapp.data.room.CurrencyMapperFromModelToEntity
import com.example.moneytestapp.data.room.CurrencyMapperFromModelToEntityImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCurrencyDao(appDatabase: AppDatabase): CurrencyDao {
        return appDatabase.currencyDao()
    }

    @Provides
    @Singleton
    fun provideCurrencyMapperFromModelToEntity(): CurrencyMapperFromModelToEntity{
        return CurrencyMapperFromModelToEntityImpl()
    }

}