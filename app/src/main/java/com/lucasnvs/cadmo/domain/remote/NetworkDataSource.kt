package com.lucasnvs.cadmo.domain.remote

import com.lucasnvs.cadmo.data.source.remote.NetworkProduct

interface NetworkDataSource {

    suspend fun loadProducts(): List<NetworkProduct>

    suspend fun saveProducts(tasks: List<NetworkProduct>)
}