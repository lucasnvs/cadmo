package com.lucasnvs.cadmo.data.source.network

interface NetworkDataSource {

    suspend fun loadProducts(): List<NetworkProduct>

    suspend fun saveProducts(tasks: List<NetworkProduct>)
}