package com.example.moneytestapp.data.room

import androidx.room.*


@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currencyentity")
    suspend fun getAll(): List<CurrencyEntity>?

    @Query("SELECT * FROM currencyentity WHERE currency_name = :currency_name")
    fun getByName(currency_name: String): CurrencyEntity


//    @Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currency: CurrencyEntity?)

    @Update
    fun update(currency: CurrencyEntity?)

    @Delete()
    suspend fun delete(currency: CurrencyEntity?)
}


@Database(entities = [CurrencyEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}