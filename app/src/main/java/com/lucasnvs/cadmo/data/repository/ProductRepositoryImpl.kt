package com.lucasnvs.cadmo.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.lucasnvs.cadmo.domain.model.Product
import com.lucasnvs.cadmo.data.source.remote.DataSection
import com.lucasnvs.cadmo.domain.remote.NetworkDataSource
import com.lucasnvs.cadmo.data.source.remote.NetworkProduct
import com.lucasnvs.cadmo.data.toExternal
import com.lucasnvs.cadmo.data.toLocal
import com.lucasnvs.cadmo.di.ApplicationScope
import com.lucasnvs.cadmo.di.DefaultDispatcher
import com.lucasnvs.cadmo.domain.local.ProductDAO
import com.lucasnvs.cadmo.domain.repository.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: ProductDAO,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
    ) : ProductRepository {
    override suspend fun refresh() {
        TODO("Not yet implemented")
    }

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

    override fun getAllFavoritesStream(): Flow<List<Product>> {
        return localDataSource.observeAll().map { products ->
            withContext(dispatcher) {
                products.toExternal()
            }
        }
    }

    override fun getFavoriteStream(favoriteId: String): Flow<Product> {
        return localDataSource.observeById(favoriteId).map { it.toExternal() }
    }

    override suspend fun getAllFavorites(): List<Product> {
        return localDataSource.getAllFavorites().toExternal()
    }

    override suspend fun getFavorite(productId: Long): Product? {
        return localDataSource.getFavorite(productId)?.toExternal()
    }

    override suspend fun addToFavorites(product: Product) {
        localDataSource.upsertFavorite(product.toLocal())
    }

    override suspend fun deleteAllFavorites(product: Product) {
        localDataSource.deleteAll()
    }

    override suspend fun deleteFromFavorites(product: Product) {
        localDataSource.deleteFavoriteById(product.id.toString())
    }

}