package com.lucasnvs.cadmo.data.source.local

import androidx.room.Entity


@Entity(
    tableName = "product"
)
data class LocalProduct (
    val id: Int,
    val name: String,
    val price: String,
    val imgSrc: String,
    val isOpenBox: Boolean,
)