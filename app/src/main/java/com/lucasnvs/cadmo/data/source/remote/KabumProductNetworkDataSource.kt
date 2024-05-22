package com.lucasnvs.cadmo.data.source.remote

import com.lucasnvs.cadmo.domain.remote.NetworkDataSource
import javax.inject.Inject

data class DataSection(
    val key: String,
    val list: List<NetworkProduct>
)

class KabumProductNetworkDataSource @Inject constructor() : NetworkDataSource {

    override suspend fun loadProducts(): List<NetworkProduct> {
        return KabumApi.retrofitService.getProducts().produtos
    }

    override suspend fun saveProducts(products: List<NetworkProduct>) {
        TODO("Not yet implemented")
    }

}