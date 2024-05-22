package com.lucasnvs.cadmo.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucasnvs.cadmo.domain.local.ProductDAO

@Database(entities = [LocalProduct::class], version = 1, exportSchema = false)
abstract class CadmoDatabase : RoomDatabase() {

    abstract fun productDAO(): ProductDAO
}