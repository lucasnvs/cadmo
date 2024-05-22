package com.lucasnvs.cadmo.data.repository

import android.util.Log
import com.lucasnvs.cadmo.domain.model.Product
import com.lucasnvs.cadmo.data.source.remote.DataSection
import com.lucasnvs.cadmo.domain.remote.NetworkDataSource
import com.lucasnvs.cadmo.data.source.remote.NetworkProduct
import com.lucasnvs.cadmo.data.toExternal
import com.lucasnvs.cadmo.data.toLocal
import com.lucasnvs.cadmo.domain.local.ProductDAO
import com.lucasnvs.cadmo.domain.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: ProductDAO
    ) : ProductRepository {

    override suspend fun getKabumProducts(): List<Product> {
        TODO()
    }

    override suspend fun getSections(): Map<String, List<Product>> {
        val products = networkDataSource.loadProducts()
        val sections = sectionIsOpenBox(products)


        val mapSections = mutableMapOf<String, List<Product>>()
        for (section in sections) {
            mapSections[section.key] = section.list.toExternal()
        }

        return mapSections
    }

    private fun sectionIsOpenBox(produtos: List<NetworkProduct>): List<DataSection> {

        return listOf(
            DataSection(key = "Mais procurados", list = produtos),
            DataSection(key = "DESCONTOS DA SEMANA", list = produtos)
        )
    }

    override suspend fun getAllFavorites(): List<Product> {
        return localDataSource.getAllFavorites().toExternal()
    }

    override suspend fun getFavorite(productId: Int): Product? {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavorites(product: Product) {
        localDataSource.upsertFavorite(product.toLocal())
        Log.d("DATABASE", "Produto de id: ${product.id} inserido com sucesso!")
    }

    override suspend fun deleteFromFavorites(product: Product) {
        localDataSource.deleteFavoriteById(product.id.toString())
    }

}