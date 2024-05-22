package com.lucasnvs.cadmo.domain.repository

import androidx.lifecycle.LiveData
import com.lucasnvs.cadmo.domain.model.Product

interface ProductRepository {

    suspend fun getKabumProducts(): List<Product>
    suspend fun getSections(): Map<String, List<Product>>
    fun getAllFavoritesStream(): LiveData<List<Product>>
    fun getFavoriteStream(): LiveData<Product>
    suspend fun getAllFavorites(): List<Product>
    suspend fun getFavorite(productId: Int): Product?
    suspend fun refresh()
    suspend fun addToFavorites(product: Product)
    suspend fun deleteAllFavorites(product: Product)
    suspend fun deleteFromFavorites(product: Product)

}