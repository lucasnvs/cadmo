package com.lucasnvs.cadmo.data

import com.lucasnvs.cadmo.data.source.network.KabumApi

class DefaultProductRepository : ProductRepository {
    override suspend fun getKabumProducts(): List<Product> {
        TODO()
    }

}