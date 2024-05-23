package com.lucasnvs.cadmo.data.source.remote

import com.lucasnvs.cadmo.domain.remote.NetworkDataSource
import javax.inject.Inject

class KabumProductNetworkDataSource @Inject constructor() : NetworkDataSource {

    private var moreSearchedList = mutableListOf<NetworkProduct>()
    private var bestRatedList = mutableListOf<NetworkProduct>()
    private var ninjaOfferList = mutableListOf<NetworkProduct>()

    private suspend fun distributeProducts() {
        val products = loadProducts()

        val numProducts = products.size
        val itemsPerList = numProducts / 3

        for (i in 0 until numProducts) {
            when (i % 3) {
                0 -> moreSearchedList.add(products[i])
                1 -> bestRatedList.add(products[i])
                else -> ninjaOfferList.add(products[i])
            }
        }
    }

    override suspend fun loadProducts(): List<NetworkProduct> {
        return KabumApi.retrofitService.getProducts().produtos
    }

    override suspend fun saveProducts(products: List<NetworkProduct>) {
        TODO("Not yet implemented")
    }

    override suspend fun getMoreSearched(): List<NetworkProduct> {
        if (moreSearchedList.isEmpty()) {
            distributeProducts()
        }

        return moreSearchedList
    }

    override suspend fun getBestRated(): List<NetworkProduct> {
        if (bestRatedList.isEmpty()) {
            distributeProducts()
        }

        return bestRatedList
    }

    override suspend fun getNinjaOffer(): List<NetworkProduct> {
        if (ninjaOfferList.isEmpty()) {
            distributeProducts()
        }

        return ninjaOfferList
    }


}