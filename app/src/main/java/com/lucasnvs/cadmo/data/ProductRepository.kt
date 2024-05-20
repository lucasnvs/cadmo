package com.lucasnvs.cadmo.data

interface ProductRepository {

    suspend fun getKabumProducts(): List<Product>

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