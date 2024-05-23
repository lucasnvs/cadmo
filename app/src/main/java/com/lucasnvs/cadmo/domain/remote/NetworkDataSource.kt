package com.lucasnvs.cadmo.domain.remote

import com.lucasnvs.cadmo.data.source.remote.NetworkProduct

interface NetworkDataSource {

    suspend fun loadProducts(): List<NetworkProduct>

    suspend fun saveProducts(tasks: List<NetworkProduct>)

    suspend fun getMoreSearched() : List<NetworkProduct>

    suspend fun getBestRated() : List<NetworkProduct>

    suspend fun getNinjaOffer() : List<NetworkProduct>
}