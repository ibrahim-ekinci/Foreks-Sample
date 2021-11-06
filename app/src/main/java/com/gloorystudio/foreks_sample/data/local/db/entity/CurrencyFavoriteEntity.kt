package com.gloorystudio.foreks_sample.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyFavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "code")
    val code: String
)
