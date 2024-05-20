package com.lucasnvs.cadmo.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "product"
)
data class LocalProduct (
    @PrimaryKey val id: Int,
    val name: String,
    val price: String,
    val imgSrc: String,
    val isOpenBox: Boolean,
)