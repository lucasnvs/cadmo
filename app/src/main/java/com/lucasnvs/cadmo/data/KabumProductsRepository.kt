package com.lucasnvs.cadmo.data

import com.lucasnvs.cadmo.model.Product
import com.lucasnvs.cadmo.network.KabumApi

interface KabumProductsRepository {
    suspend fun getKabumProducts(): List<Product>
}

class NetworkKabumProductsRepository(): KabumProductsRepository {
    override suspend fun getKabumProducts(): List<Product> {
        return KabumApi.retrofitService.getProducts().produtos
    }
}