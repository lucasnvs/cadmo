package com.lucasnvs.cadmo.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "favorites"
)
data class LocalProduct (
    @PrimaryKey val id: Long,
    val name: String,
    val price: String,
    val imgSrc: String,
)