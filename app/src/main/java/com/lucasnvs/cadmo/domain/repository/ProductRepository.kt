package com.lucasnvs.cadmo.domain.repository

import com.lucasnvs.cadmo.domain.model.Product

interface ProductRepository {

    suspend fun getKabumProducts(): List<Product>
    suspend fun getSections(): Map<String, List<Product>>

//    suspend fun getAllFavoritesStream(): LiveData<List<Product>>
    suspend fun getAllFavorites(): List<Product>
    suspend fun getFavorite(productId: Int): Product?
    suspend fun addToFavorites(product: Product)
    suspend fun deleteFromFavorites(product: Product)

//    fun getTasksStream(): Flow<List<Task>>
//
//    suspend fun getTasks(forceUpdate: Boolean = false): List<Task>
//
//    suspend fun refresh()
//
//    fun getTaskStream(taskId: String): Flow<Task?>
//
//    suspend fun getTask(taskId: String, forceUpdate: Boolean = false): Task?
//
//    suspend fun refreshTask(taskId: String)
//
//    suspend fun deleteAllTasks()
//
//    suspend fun deleteTask(taskId: String)

}