package com.lucasnvs.cadmo.domain.repository

import com.lucasnvs.cadmo.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun refresh()
    suspend fun getKabumProducts(): List<Product>
    suspend fun getSections(): Map<String, List<Product>>
    fun getAllFavoritesStream(): Flow<List<Product>>
    fun getFavoriteStream(favoriteId: String): Flow<Product>
    suspend fun getAllFavorites(): List<Product>
    suspend fun getFavorite(productId: Long): Product?
    suspend fun addToFavorites(product: Product)
    suspend fun deleteAllFavorites(product: Product)
    suspend fun deleteFromFavorites(product: Product)

}