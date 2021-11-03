package com.gloorystudio.foreks_sample.data.local.db.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gloorystudio.foreks_sample.data.local.db.entity.CurrencyFavoriteEntity

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyFavorite(currencyFavoriteEntity: CurrencyFavoriteEntity)

    @Query("DELETE FROM currencyFavoriteEntity WHERE tke=:tke")
    suspend fun deleteCurrencyFavorite(tke: String)

    @Query("SELECT * FROM currencyFavoriteEntity")
    suspend fun getAllCurrencyFavorite(): List<CurrencyFavoriteEntity>?
}