package com.lucasnvs.cadmo.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lucasnvs.cadmo.data.source.local.LocalProduct


@Dao
interface ProductDAO {
    @Query("SELECT * FROM product")
    suspend fun getAll(): List<LocalProduct>

}