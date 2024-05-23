package com.lucasnvs.cadmo.domain.local

import androidx.room.Dao
import androidx.room.Upsert
import androidx.room.Query
import com.lucasnvs.cadmo.data.source.local.LocalProduct
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDAO {

    @Query("SELECT * FROM favorites")
    fun observeAll(): Flow<List<LocalProduct>>
    @Query("SELECT * FROM favorites WHERE id = :favoriteId")
    fun observeById(favoriteId: String): Flow<LocalProduct>
    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<LocalProduct>
    @Query("SELECT * FROM favorites WHERE id = :id")
    suspend fun getFavorite(id: Long): LocalProduct?
    @Upsert
    suspend fun upsertFavorite(product: LocalProduct)
    @Query("DELETE FROM favorites WHERE id = :favoriteId")
    suspend fun deleteFavoriteById(favoriteId: String): Int
    @Query("DELETE FROM favorites")
    suspend fun deleteAll()

}