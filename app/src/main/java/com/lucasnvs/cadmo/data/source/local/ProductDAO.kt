package com.lucasnvs.cadmo.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lucasnvs.cadmo.data.source.local.LocalProduct


@Dao
interface ProductDAO {
    @get:Query("SELECT * FROM product")
    val all: List<Any?>?

    @Insert
    fun insert(vararg contatos: LocalProduct?)

    @Delete
    fun delete(contato: LocalProduct?)
}