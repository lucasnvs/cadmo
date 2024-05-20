package com.lucasnvs.cadmo.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucasnvs.cadmo.data.source.local.LocalProduct


@Database(entities = [LocalProduct::class], version = 1)
abstract class CadmoDatabase : RoomDatabase() {

    abstract fun productDAO(): ProductDAO
}