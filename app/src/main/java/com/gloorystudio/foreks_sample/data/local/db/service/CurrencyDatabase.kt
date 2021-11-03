package com.gloorystudio.foreks_sample.data.local.db.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gloorystudio.foreks_sample.data.local.db.entity.CurrencyFavoriteEntity

@Database(entities = [CurrencyFavoriteEntity::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {
        @Volatile
        private var instance: CurrencyDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CurrencyDatabase::class.java,
            "gameDatabase"
        ).build()
    }
}