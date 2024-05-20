package com.lucasnvs.cadmo.data

import com.lucasnvs.cadmo.data.source.local.ProductDAO
import com.lucasnvs.cadmo.data.source.network.DataSection
import com.lucasnvs.cadmo.data.source.network.NetworkDataSource
import com.lucasnvs.cadmo.data.source.network.NetworkProduct
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultProductRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
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

        var listIsOpenBox = mutableListOf<NetworkProduct>()
        var listNotIsOpenBox = mutableListOf<NetworkProduct>()

        for (produto in produtos) {
            val check = produto.isOpenBox

            if(check) {
                listIsOpenBox.add(produto)
            } else {
                listNotIsOpenBox.add(produto)
            }
        }

        return listOf(
            DataSection(key = "Mais procurados", list = listIsOpenBox),
            DataSection(key = "DESCONTOS DA SEMANA", list = listNotIsOpenBox)
        )
    }
}